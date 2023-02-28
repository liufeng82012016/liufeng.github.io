const {defineConfig} = require('@vue/cli-service')
const AutoImport = require('unplugin-auto-import/webpack')
const Components = require('unplugin-vue-components/webpack')
const {ElementPlusResolver} = require('unplugin-vue-components/resolvers')
module.exports = defineConfig({
    transpileDependencies: true,
    devServer: {
        // 代理配置
        proxy: 'http://localhost:8081'
    },
    outputDir: './build',
    configureWebpack: {
        resolve: {
            alias: {
                components: "@/components"
            }
        },
        plugins: [
            AutoImport({
                resolvers: [ElementPlusResolver()]
            }),
            Components({
                resolvers: [ElementPlusResolver()]
            })
        ]
    }
})
