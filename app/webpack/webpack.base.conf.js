var path = require('path');
var config = require('./index');
var utils = require('./utils');
var projectRoot = path.resolve(__dirname, '../');

var webpack = require('webpack');
var CleanWebpackPlugin = require('clean-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var CopyWebpackPlugin = require('copy-webpack-plugin');
var HtmlWebpackExcludeAssetsPlugin = require('html-webpack-exclude-assets-plugin');
var HtmlWebpackInlineSourcePlugin = require('html-webpack-inline-source-plugin');

module.exports = {
    entry: {
        app: './resources/app/app.ts'
    },
    output: {
        path: config.build.assetsRoot,
        publicPath: 'resources/app/',
        filename: utils.assetsPath('js/[name].[chunkhash].js'),
        chunkFilename: utils.assetsPath('js/[id].[chunkhash].js')
    },
    resolve: {
        extensions: ['.ts', '.js', '.json'],
        alias: {
            'vue$': 'vue/dist/vue.common.js',
            'src': path.resolve(__dirname, '../src'),
            'assets': path.resolve(__dirname, '../src/assets'),
            'components': path.resolve(__dirname, '../src/components')
        },
        modules: [
            path.resolve(__dirname, '../node_modules')
        ]
    },
    plugins: [
        new CleanWebpackPlugin([
            'css', 'js'
        ], {
            root: config.build.assetsRoot,
            verbose: true,
            dry: false
        }),
        new webpack.optimize.OccurrenceOrderPlugin(),
        new ExtractTextPlugin(utils.assetsPath('css/[name].[contenthash].css')),
        new HtmlWebpackPlugin({
            filename: config.build.index,
            template: 'html-loader!./resources/templates/imports.ftl',
            inject: true,
            chunksSortMode: 'dependency'
        }),
        new HtmlWebpackExcludeAssetsPlugin(),
        new HtmlWebpackInlineSourcePlugin(),
        new webpack.optimize.CommonsChunkPlugin({
            name: 'vendor',
            minChunks: function (module, count) {
                return (
                    module.resource &&
                    /\.js$/.test(module.resource) &&
                    module.resource.indexOf(
                        path.join(__dirname, '../node_modules')
                    ) === 0
                )
            }
        }),
        new webpack.optimize.CommonsChunkPlugin({
            name: 'manifest',
            chunks: ['vendor']
        }),
        new CopyWebpackPlugin([{
            from: './resources/images/**/*',
            to: 'images',
            flatten: true
        }, {
            from: './resources/data/*',
            to: 'data',
            flatten: true
        }, {
            from: './resources/vendor/*',
            to: 'vendor',
            flatten: true
        }, {
            from: './resources/scss/fonts/**/*',
            to: 'css/fonts',
            flatten: true
        }])
    ],
    module: {
        loaders: [
            {
                test: /\.ts$/,
                loader: 'ts-loader'
            },
            {
                test: /\.js$/,
                loader: 'babel-loader',
                include: projectRoot,
                exclude: /node_modules/
            },
            {
                test: /\.json$/,
                loader: 'json-loader'
            },
            {
                test: /\.(html|vue)$/,
                loader: 'html-loader'
            },
            {
                test: /\.(jpg|jpeg|gif|png|svg)$/,
                exclude: /node_modules/,
                loader: 'file-loader?limit=1024&name=images/[name].[ext]'
            },
            {
                test: /\.(woff|woff2|eot|ttf|otf)(\?.*)?$/,
                exclude: /node_modules/,
                loader: 'file-loader?limit=1024&name=[name].[ext]',
                options: {
                    publicPath: "../",
                    outputPath: "css/fonts/"
                }
            }
        ]
    }
};
