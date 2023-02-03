// 导入node.js中专门用于操作路径的模块
const path = require('path');


// 导入HTML插件，得到一个构造函数
const HtmlPlugin = require('html-webpack-plugin')
// 创建HTML插件的实例对象
const htmlPlugin = new HtmlPlugin({
    // 指定源文件路径
    template: './src/index.html',
    // 指定生成的文件存放路径
    filename: "./index.html"
})


module.exports = {
    // development和production可选
    mode: 'development',
    // 开发环境sourceMap webpack5好像已经集成
    // devtool:'eval-source-map',
    // devtool:'nosources-source-map', // 生产环境只暴露行号
    // devtool:'source-map', // 生产环境暴露源代码
    // 指定打包入口文件的路径，默认./src/index.js
    entry: path.join(__dirname, './src/index.js'),
    // 指定输出文件目录和名称 默认./dist/main.js
    output: {
        path: path.join(__dirname, './dist'),
        filename: 'js/main.js'
    },
    plugins: [
        // 挂载插件
        htmlPlugin
    ],
    devServer: {
        // 初次打包后自动在网页打开，并指定ip和端口
        open: true,
        host: '127.0.0.1',
        port: 8081,
    },

    // 使用loader
    module: {
        rules: [
            {
                // 正则匹配css文件
                test: /\.css$/,
                use: [
                    // 顺序是固定的，从后往前调用
                    'style-loader',
                    'css-loader',
                ]
            },
            {
                // 正则匹配css文件
                test: /\.less$/,
                use: [
                    // 顺序是固定的，从后往前调用
                    'style-loader',
                    'css-loader',
                    'less-loader'
                ]
            },
            // webpack5.4.1加这个loader配置，图片无法加载出来，不加反而正常
            // {
            //     // 正则匹配css文件
            //     test: /\.jpg|png|gif$/,
            //     // use: [
            //     //     // limit用来指定图片大小，单位是字节，只有小于等于limit的图片，才会被转为base64格式的图片
            //     //     'url-loader?limit=272290',
            //     // ]
            //     use: {
            //         loader: 'url-loader',
            //         options: {
            //             limit: 27229,
            //         }
            //     }
            // },
            // {
            //     // babel
            //     test: /\.js$/,
            //     exclude: /node_modules/,
            //     use: {
            //         loader: 'babel-loader',
            //         options: {
            //             plugins: ['@babel/plugin-proposal-class-properties']
            //         }
            //     }
            // }
        ]
    }
}