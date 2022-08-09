package liufeng.design.pattern.observer;

import org.junit.Test;

import java.util.LinkedList;

/**
 * 天气气象站
 * 由WeatherData追踪目前的天气状况（温度、湿度、气压）
 * 另外建立一个应用，有3种布告板，分别显示当前的状况、气象统计及简单的预报。当WeatherData更新时，三种布告板必须即时更新
 * 而且这是可扩展的气象站，可公布一组api，让其他的开发人员可以写出自己的气象布告板
 */
public class WeatherData implements Subject {

    private LinkedList<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementChanged();
    }

    public WeatherData() {
        observers = new LinkedList<>();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    /**
     * 当气象更新时，调用此方法
     */
    public void measurementChanged() {
        // v1：更新3个布告板
        // currentConditionDisplay.update(...);
        // statisticsDisplay.update(...);
        // forecastDisplay.update(...);
        // v2：WeatherData implements Subject
        // currentConditionDisplay/forecastDisplay/forecastDisplay注册为WeatherData的观察者
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = observers.get(i);
            observer.update(this.temperature, this.humidity, this.pressure);
        }
    }

    @Test
    public void run() {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        weatherData.setMeasurements(60,65,30.4f);
    }
}

/**
 * 不使用Java API，而是尝试自己实现
 */
interface Subject {
    // 观察者注册
    public void registerObserver(Observer observer);

    // 观察者取消注册
    public void removeObserver(Observer observer);

    // 通知观察者
    public void notifyObservers();
}

interface Observer {
    // 每个布告板都有一个update方法
    public void update(float temperature, float humidity, float pressure);
}

interface DisplayElement {
    public void display();
}

class CurrentConditionDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private float pressure;
    private Subject weatherData;

    public CurrentConditionDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.temperature = temperature;
        display();
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "CurrentConditionDisplay{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", weatherData=" + weatherData +
                '}';
    }
}

class ForecastDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private float pressure;
    private Subject weatherData;

    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.temperature = temperature;
        display();
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "ForecastDisplay{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", weatherData=" + weatherData +
                '}';
    }
}

class StatisticsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private float pressure;
    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.temperature = temperature;
        display();
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "StatisticsDisplay{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", weatherData=" + weatherData +
                '}';
    }
}




