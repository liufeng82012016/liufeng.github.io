## Spring Boot 启动流程
### 非web应用  
1. org.springframework.boot.SpringApplication.SpringApplication.run(Class<?> primarySource, String... args);
2. org.springframework.boot.SpringApplication.SpringApplication.run(Class<?>[] primarySources, String... args);
3. new SpringApplication(primarySources).run(args);
```text
// 创建SpringApplication
public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
      this.resourceLoader = resourceLoader;// null
      Assert.notNull(primarySources, "PrimarySources must not be null");
      this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));
      this.webApplicationType = WebApplicationType.deduceFromClasspath();
      // 创建初始化器 ApplicationContextInitializer
      setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
      // 创建监听器 ApplicationListener
      setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
      this.mainApplicationClass = deduceMainApplicationClass();
}

// getSpringFactoriesInstances ：org.springframework.core.io.support.SpringFactoriesLoader.loadSpringFactories
private static Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader) {
        // 如果缓存有数据，直接返回
		MultiValueMap<String, String> result = cache.get(classLoader);
		if (result != null) {
			return result;
		}
		try {
            // FACTORIES_RESOURCE_LOCATION:META-INF/spring.factories
            // 尝试去加载spring.factories文件传
			Enumeration<URL> urls = (classLoader != null ?
					classLoader.getResources(FACTORIES_RESOURCE_LOCATION) :
					ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));
			result = new LinkedMultiValueMap<>();
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				// spring-starter从此加载。比如说 jar:file:/Users/liufeng/.m2/repository/com/baomidou/mybatis-plus-boot-starter/3.4.3.4/mybatis-plus-boot-starter-3.4.3.4.jar!/META-INF/spring.factories
				UrlResource resource = new UrlResource(url);
				Properties properties = PropertiesLoaderUtils.loadProperties(resource);
				// mybatis-plus定义了2个参数
				// org.springframework.boot.env.EnvironmentPostProcessor：com.baomidou.mybatisplus.autoconfigure.SafetyEncryptProcessor
				// org.springframework.boot.autoconfigure.EnableAutoConfiguration ： com.baomidou.mybatisplus.autoconfigure.IdentifierGeneratorAutoConfiguration,com.baomidou.mybatisplus.autoconfigure.MybatisPlusLanguageDriverAutoConfiguration,com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration
				for (Map.Entry<?, ?> entry : properties.entrySet()) {
					String factoryTypeName = ((String) entry.getKey()).trim();
					for (String factoryImplementationName : StringUtils.commaDelimitedListToStringArray((String) entry.getValue())) {
						result.add(factoryTypeName, factoryImplementationName.trim());
					}
				}
			}
			cache.put(classLoader, result);
			return result;
		}
		catch (IOException ex) {
			throw new IllegalArgumentException("Unable to load factories from location [" +
					FACTORIES_RESOURCE_LOCATION + "]", ex);
		}
	}
// 上一步加载后，利用反射创建Initializer的实例
private <T> List<T> createSpringFactoriesInstances(Class<T> type, Class<?>[] parameterTypes,
			ClassLoader classLoader, Object[] args, Set<String> names) {
		List<T> instances = new ArrayList<>(names.size());
		for (String name : names) {
			try {
				Class<?> instanceClass = ClassUtils.forName(name, classLoader);
				Assert.isAssignable(type, instanceClass);
				Constructor<?> constructor = instanceClass.getDeclaredConstructor(parameterTypes);
				T instance = (T) BeanUtils.instantiateClass(constructor, args);
				instances.add(instance);
			}
			catch (Throwable ex) {
				throw new IllegalArgumentException("Cannot instantiate " + type + " : " + name, ex);
			}
		}
		return instances;
	}
```

4. org.springframework.boot.SpringApplication.run(java.lang.String...)
   1. org.springframework.util.StopWatch.start()
      1. 初始化任务，记录当前的纳秒？
   2. org.springframework.boot.SpringApplication.configureHeadlessProperty
      1. 检查系统配置，为啥是awt相关，没懂
   3. org.springframework.boot.SpringApplication.getRunListeners
      1. 初始化监听器 EventPublishingRunListener
      2. listeners.starting
         1. org.springframework.boot.autoconfigure.BackgroundPreinitializer.onApplicationEvent
   4. org.springframework.boot.SpringApplication.prepareEnvironment（准备环境，和webApplicationType参数有关）
      1. webApplicationType=SERVLET  StandardServletEnvironment
      2. webApplicationType=REACTIVE StandardReactiveWebEnvironment
      3. 否则 StandardEnvironment
   5. org.springframework.boot.SpringApplication.configureIgnoreBeanInfo（设置系统属性 spring.beaninfo.ignore）
   6. org.springframework.boot.SpringApplication.printBanner 获取banner
      1. this.bannerMode == Banner.Mode.OFF 关闭则不打印
   7. org.springframework.boot.SpringApplication.createApplicationContext
      1. 根据webApplicationType，确定生成哪个类型的context
         1. webApplicationType=SERVLET  org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext
         2. webApplicationType=REACTIVE org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebServerApplicationContext
         3. 否则 StandardEnvironment org.springframework.context.annotation.AnnotationConfigApplicationContext
      2. org.springframework.beans.BeanUtils.instantiateClass(java.lang.Class<T>) （根据反射创建bean）
         1. 设置构造器可访问 org.springframework.util.ReflectionUtils.makeAccessible(java.lang.reflect.Constructor<?>)
         2. 判断是否Kotlin？？
         3. 注入构造器参数，调用构造器的newInstance方法 ，AnnotationConfigApplicationContext的属性
            1. AnnotatedBeanDefinitionReader reader
            2. ClassPathBeanDefinitionScanner scanner
            3. DefaultListableBeanFactory beanFactory（父类 org.springframework.context.support.GenericApplicationContext）
            4. ResourcePatternResolver resourcePatternResolver（父类 org.springframework.context.support.AbstractApplicationContext）
            5. ClassLoader classLoader = org.springframework.util.ClassUtils.getDefaultClassLoader（父类 org.springframework.core.io.DefaultResourceLoader）
   8. org.springframework.boot.SpringApplication.prepareContext    
      1. context.setEnvironment(environment);
      2. postProcessApplicationContext(context); 
      3. applyInitializers(context);(org.springframework.context.ApplicationContextInitializer.initialize)
         1. SharedMetadataReaderFactoryContextInitializer
         2. DelegatingApplicationContextInitializer
         3. ContextIdApplicationContextInitializer
         4. ConditionEvaluationReportLoggingListener
         5. ConfigurationWarningsApplicationContextInitializer
         6. RSocketPortInfoApplicationContextInitializer
         7. ServerPortInfoApplicationContextInitializer 
      4. listeners.contextPrepared(context);
      5. 打印日志 active profile if (this.logStartupInfo) { logStartupInfo(context.getParent() == null); logStartupProfileInfo(context); }
      6. 注册singleton bean，设置一些属性
         1. // Add boot specific singleton beans 
         2. ConfigurableListableBeanFactory beanFactory = context.getBeanFactory(); 
         3. beanFactory.registerSingleton("springApplicationArguments", applicationArguments); 
         4. if (printedBanner != null) { beanFactory.registerSingleton("springBootBanner", printedBanner); } 
         5. if (beanFactory instanceof DefaultListableBeanFactory) {((DefaultListableBeanFactory) beanFactory).setAllowBeanDefinitionOverriding(this.allowBeanDefinitionOverriding); } 
         6. if (this.lazyInitialization) { context.addBeanFactoryPostProcessor(new LazyInitializationBeanFactoryPostProcessor()); }
      7. 加载应用
         1. // Load the sources ；sources就是SpringBootApplication.run方法传入的class参数
         2. Set<Object> sources = getAllSources(); 
         3. Assert.notEmpty(sources, "Sources must not be empty"); 
         4. load(context, sources.toArray(new Object[0])); 
            1. 打印debug日志 if (logger.isDebugEnabled()) { logger.debug("Loading source " + StringUtils.arrayToCommaDelimitedString(sources)); } 
            2. 初始化加载起 BeanDefinitionLoader loader = createBeanDefinitionLoader(getBeanDefinitionRegistry(context), sources); 
            3. if (this.beanNameGenerator != null) { loader.setBeanNameGenerator(this.beanNameGenerator); } 
            4. if (this.resourceLoader != null) { loader.setResourceLoader(this.resourceLoader); } 
            5. if (this.environment != null) { loader.setEnvironment(this.environment); } 
            6. loader.load(); // 居然有4种实现 class，resource，package，charSequence
               1. Assert.notNull(source, "Source must not be null"); 
               2. if (source instanceof Class<?>) {return load((Class<?>) source); } 
                  1. this.annotatedReader.register(source); org.springframework.context.annotation.AnnotatedBeanDefinitionReader.register
                  2. org.springframework.context.annotation.AnnotatedBeanDefinitionReader.doRegisterBean
                     1. AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(beanClass); 
                     2. 判断是否跳过 if (this.conditionEvaluator.shouldSkip(abd.getMetadata())) { return; } 
                     3. abd.setInstanceSupplier(supplier); 
                     4. 解析 scope ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(abd); abd.setScope(scopeMetadata.getScopeName());
                        1. ScoreMetadata默认单例 private String scopeName = BeanDefinition.SCOPE_SINGLETON; 
                        2. ScoreMetadata默认不开启代理？ private ScopedProxyMode scopedProxyMode = ScopedProxyMode.NO;
                        3. 如果使用了Scope注解，会使用注解的值进行覆盖
                     5. 解析bean名称 String beanName = (name != null ? name : this.beanNameGenerator.generateBeanName(abd, this.registry));
                        1. 从类注解获取，如果有注解 org.springframework.stereotype.Component/javax.annotation.ManagedBean/javax.inject.Named且为单例，取value()值作为beanName
                        2. 如果没有获取到，会使用类名称生成bean的名称
                     6. 启动应用设置一些参数 org.springframework.context.annotation.AnnotationConfigUtils.processCommonDefinitionAnnotations(org.springframework.beans.factory.annotation.AnnotatedBeanDefinition, org.springframework.core.type.AnnotatedTypeMetadata)
                        1. 设置懒加载 org.springframework.context.annotation.Lazy
                        2. 设置Primary org.springframework.context.annotation.Primary
                        3. 设置DependOn org.springframework.context.annotation.DependsOn
                        4. 设置Role org.springframework.context.annotation.Role
                        5. 设置Description org.springframework.context.annotation.Description
                     7. 设置Primary/Lazy 等，重复设置？？ 
                     8. customizers 还不知道是啥，目前没用到
                     9. BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd, beanName); 
                     10. 使用代理？？ definitionHolder = AnnotationConfigUtils.applyScopedProxyMode(scopeMetadata, definitionHolder, this.registry); 
                     11. 注册Bean的名称和别名 BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, this.registry);
                         1. org.springframework.beans.factory.support.BeanDefinitionRegistry.registerBeanDefinition
                            1. 如果是 instanceof AbstractBeanDefinition 需要校验；org.springframework.beans.factory.support.AbstractBeanDefinition.validate 
                            2. 获取Bean BeanDefinition existingDefinition = this.beanDefinitionMap.get(beanName);存在Bean，进入小校验
                               1. 校验Bean是否可以重写；若否，抛出异常
                               2. 校验role，打印日志； ROLE_APPLICATION, ROLE_SUPPORT, ROLE_INFRASTRUCTURE对应0-2
                               3. 将新的BeanDefinition放到beanDefinitionMap
                            3. existingDefinition不存在则加入map
                         2. org.springframework.core.AliasRegistry.registerAlias
               3. if (source instanceof Resource) { return load((Resource) source); } 
               4. if (source instanceof Package) { return load((Package) source); } 
               5. if (source instanceof CharSequence) { return load((CharSequence) source); }
      8. listeners.contextLoaded(context);
         1. org.springframework.boot.context.event.EventPublishingRunListener.contextLoaded
            1. 遍历Listenerfor (ApplicationListener<?> listener : this.application.getListeners()) { 
            2. ApplicationContextAware注入context  if (listener instanceof ApplicationContextAware) {((ApplicationContextAware) listener).setApplicationContext(context); } 
            3. 添加Listener context.addApplicationListener(listener); } 
            4. 多播事件？？调用了listener对应的方法 this.initialMulticaster.multicastEvent(new ApplicationPreparedEvent(this.application, this.args, context));
               1. 遍历listener，调用oApplication（event）方法
   9. org.springframework.boot.SpringApplication.refreshContext
      1. 向JVM注册关闭钩子 org.springframework.context.support.AbstractApplicationContext.registerShutdownHook
      2. 刷新上下文 org.springframework.boot.SpringApplication.refresh(org.springframework.context.ApplicationContext)
      3. 真正刷新 org.springframework.context.support.AbstractApplicationContext.refresh
   10. 刷新后处理 org.springframework.boot.SpringApplication.afterRefresh
   11. 结束任务 org.springframework.util.StopWatch.stop
   12. listeners.start(context);
   13. 运行ApplicationRunner/CommandLineRunner的run方法 callRunners(context,applicationArguments);
   14. listeners.running(context);

### org.springframework.context.support.AbstractApplicationContext.refresh
```java
public abstract class AbstractApplicationContext extends DefaultResourceLoader
        implements ConfigurableApplicationContext { 
    @Override
	public void refresh() throws BeansException, IllegalStateException {
		synchronized (this.startupShutdownMonitor) {
			// Prepare this context for refreshing.
			prepareRefresh();

			// Tell the subclass to refresh the internal bean factory.
			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

			// Prepare the bean factory for use in this context.
			prepareBeanFactory(beanFactory);

			try {
				// Allows post-processing of the bean factory in context subclasses.
				postProcessBeanFactory(beanFactory);

				// Invoke factory processors registered as beans in the context.
				invokeBeanFactoryPostProcessors(beanFactory);

				// Register bean processors that intercept bean creation.
				registerBeanPostProcessors(beanFactory);

				// Initialize message source for this context.
				initMessageSource();

				// Initialize event multicaster for this context.
				initApplicationEventMulticaster();

				// Initialize other special beans in specific context subclasses.
				onRefresh();

				// Check for listener beans and register them.
				registerListeners();

				// Instantiate all remaining (non-lazy-init) singletons.
				finishBeanFactoryInitialization(beanFactory);

				// Last step: publish corresponding event.
				finishRefresh();
			}

			catch (BeansException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("Exception encountered during context initialization - " +
							"cancelling refresh attempt: " + ex);
				}

				// Destroy already created singletons to avoid dangling resources.
				destroyBeans();

				// Reset 'active' flag.
				cancelRefresh(ex);

				// Propagate exception to caller.
				throw ex;
			}

			finally {
				// Reset common introspection caches in Spring's core, since we
				// might not ever need metadata for singleton beans anymore...
				resetCommonCaches();
			}
		}
	}
}
```
1. 上synchronized锁
2. org.springframework.context.support.AbstractApplicationContext.prepareRefresh
   1. 记录开始时间
   2. 初始化properties
   3. 校验properties
   4. 添加listener
3. 好像只设置了一个标记 org.springframework.context.support.AbstractApplicationContext.obtainFreshBeanFactory
4. org.springframework.context.support.AbstractApplicationContext.prepareBeanFactory
   1. 设置BeanClassLoader
   2. 设置BeanExpressionResolver
   3. 设置PropertyEditorRegistrar
   4. add BeanPostProcessor
   5. ignoreDependencyInterface
   6. registerResolvableDependency
   7. 注入Environment
   8. 注入SystemProperties
   9. 注入SystemEnvironment
5. 允许在上下文子类中对bean工厂进行后处理。没懂！！！！ postProcessBeanFactory
6. 调用在上下文中注册为bean的工厂处理器，没懂！！！！   invokeBeanFactoryPostProcessors
   1. 前置处理，待续......
   2. 这里会解析ConfigurationClassPostProcessor （扫描 @Configuration注解对应的类吗？？） org.springframework.context.annotation.ConfigurationClassPostProcessor.processConfigBeanDefinitions
      1. 解析配置类 路径如下：（ org.springframework.context.annotation.ConfigurationClassParser.doProcessConfigurationClass）
         1. 获取@Component注解下的嵌套bean
         2. @PropertySources处理
         3. 获取@ComponentScan对应的包路径，如果不为空 org.springframework.context.annotation.ComponentScanAnnotationParser.parse
            1. 进行一系列参数处理：nameGenerator/scopedProxy/scopeResolver/resourcePattern/includeFilters/excludeFilters/lazyInit/basePackages/basePackageClasses
            2. 进行包扫描 org.springframework.context.annotation.ClassPathBeanDefinitionScanner.doScan
               1. org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider.scanCandidateComponents
                  1. 拼接正则表达式： classpath*:com/my/liufeng/chat/**/*.class
                  2. 根据表达式获取对应的class，封装成Resource
                  3. 遍历class类，和内置的filter比较。如果和ExcludeFilter匹配，标识被排除；和IncludeFilter匹配，表示需要实例化。如果都不匹配，按照排除处理
               2. 遍历得到的对象，设置Scope，BeanName（如果没有通过注解设置，使用BeanNameGenerator生成）
               3. 还没懂处理啥，看起来是注入依赖
                  1. 代码：if (candidate instanceof AbstractBeanDefinition) { postProcessBeanDefinition((AbstractBeanDefinition) candidate, beanName); }
                  2. 注释：Apply further settings to the given bean definition, beyond the contents retrieved from scanning the component class.
                  3. 注释翻译：对给定的bean定义应用进一步的设置，超出扫描组件类检索到的内容。
               4. 还没懂处理啥 看起来是处理@Primary/@Lazy/@DependOn@Role/@Description注解
                  1. 代码：if (candidate instanceof AnnotatedBeanDefinition) {AnnotationConfigUtils.processCommonDefinitionAnnotations((AnnotatedBeanDefinition) candidate);}
                  2. org.springframework.context.annotation.AnnotationConfigUtils.processCommonDefinitionAnnotations(org.springframework.beans.factory.annotation.AnnotatedBeanDefinition)
               5. 校验Bean 
                  1. 引用：org.springframework.context.annotation.ClassPathBeanDefinitionScanner.checkCandidate
                  2. 注释：Check the given candidate's bean name, determining whether the corresponding bean definition needs to be registered or conflicts with an existing definition.
                  3. 注释：检查给定候选者的bean名称，确定是否需要注册相应的bean定义或与现有定义冲突。
               6. 生成BeanDefinitionHolder，注册BeanDefinition
               7. 对返回的BeanDefinitionHolder进行遍历，如果是配置类或者Component，自动去注册（这里是递归处理，超级套娃）
         4. 处理@Import注解
         5. 处理@ImportzResource注解
         6. 处理@Bean的方法
         7. 处理接口的default方法
         8. 处理父类的方法
   3. 自动装配？？org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader.loadBeanDefinitions
   4. ImportRegistry 没太懂...  if (sbr != null && !sbr.containsSingleton(IMPORT_REGISTRY_BEAN_NAME)) {sbr.registerSingleton(IMPORT_REGISTRY_BEAN_NAME, parser.getImportRegistry()); }
   5. 
7. 注册拦截bean创建的bean处理器   registerBeanPostProcessors
8. 为此上下文初始化事件多播        initApplicationEventMulticaster
9. 初始化特定上下文子类中的其他特殊bean         onRefresh
10. 注册监听器  registerListeners
11. 实例化所有剩余的（非惰性初始化）单例         finishBeanFactoryInitialization
12. 最后一步，发布相应事件   finishRefresh
    1. 清除上下文级别的资源缓存（例如来自扫描的ASM元数据）  clearResourceCaches
    2. 为此上下文初始化lifecycle processor             initLifecycleProcessor
    3. 首先将刷新传播到生命周期处理器                    getLifecycleProcessor().onRefresh()
    4. 发布刷新上下文的时间                             publishEvent
    5. LiveBeansView特殊处理，还不知道干啥？
13. 

### 代码注释
1. BeanDefinitionHolder
```text
Holder for a BeanDefinition with name and aliases. Can be registered as a placeholder for an inner bean.
Can also be used for programmatic registration of inner bean definitions. If you don't care about BeanNameAware and the like, registering RootBeanDefinition or ChildBeanDefinition is good enough.

带有名称和别名的BeanDefinition的持有者。可以注册为内部bean的占位符。
还可以用于内部bean定义的编程注册。如果您不关心BeanNameware之类的东西，那么注册RootBeanDefinition或ChildBeanDefinition就足够了。
```
2. 注册关闭事件
```text
Register a shutdown hook named SpringContextShutdownHook with the JVM runtime, closing this context on JVM shutdown unless it has already been closed at that time.
Delegates to doClose() for the actual closing procedure.

在JVM运行时注册一个名为SpringContextShutdowHook的关闭钩子，在JVM关闭时关闭此上下文，除非它当时已经关闭。
委托doClose（）执行实际关闭过程。
```
3. 忽略自动关联的给定依赖项接口
```text
Ignore the given dependency interface for autowiring.
This will typically be used by application contexts to register dependencies that are resolved in other ways, like BeanFactory through BeanFactoryAware or ApplicationContext through ApplicationContextAware.
By default, only the BeanFactoryAware interface is ignored. For further types to ignore, invoke this method for each type.
 
忽略自动关联的给定依赖项接口。

这通常由应用程序上下文用于注册以其他方式解析的依赖项，如通过BeanFactoryAware解析的BeanFactory或通过ApplicationContextAware解析的ApplicationContext。

默认情况下，仅忽略BeanFactoryAware接口。对于要忽略的其他类型，请为每个类型调用此方法。
```
4. class关联类
```text
Register a special dependency type with corresponding autowired value.
This is intended for factory/context references that are supposed to be autowirable but are not defined as beans in the factory: e.g. a dependency of type ApplicationContext resolved to the ApplicationContext instance that the bean is living in.
Note: There are no such default types registered in a plain BeanFactory, not even for the BeanFactory interface itself

使用相应的自动连线值注册特殊依赖项类型。

这适用于工厂/上下文引用，这些引用应该是可自动编辑的，但在工厂中未定义为bean：例如，类型为ApplicationContext的依赖项解析为bean所在的ApplicationContext实例。

注意：普通BeanFactory中没有注册这样的默认类型，甚至BeanFactory接口本身也没有
```
     