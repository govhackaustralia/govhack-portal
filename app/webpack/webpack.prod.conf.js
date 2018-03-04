var config = require('./index');
var utils = require('./utils');
var webpack = require('webpack');
var merge = require('webpack-merge');
var UglifyJsPlugin = require('uglifyjs-webpack-plugin');

var baseWebpackConfig = require('./webpack.base.conf');

var webpackConfig = merge(baseWebpackConfig, {
    module: {
        loaders: utils.styleLoaders({sourceMap: false, extract: true})
    },
    devtool: false,
    plugins: [
        new webpack.DefinePlugin({
            'process.env': config.build.env
        }),
        new webpack.LoaderOptionsPlugin({
            vue: {
                loaders: utils.cssLoaders({
                    sourceMap: false,
                    extract: true
                })
            }
        }),
        new UglifyJsPlugin({
            uglifyOptions: {
                output: {
                    comments: false
                },
                compress: {
                    warnings: false,
                    drop_console: true
                }
            }
        })
    ]
});

module.exports = webpackConfig;
