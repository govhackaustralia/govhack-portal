const autoprefixer = require('autoprefixer');
const ExtractTextPlugin = require('extract-text-webpack-plugin');

const path = require('path');
const config = require('./index');

exports.assetsPath = function (_path) {
    const assetsSubDirectory = process.env.NODE_ENV === 'production'
        ? config.build.assetsSubDirectory
        : config.dev.assetsSubDirectory;
    return path.posix.join(assetsSubDirectory, _path);
};

exports.cssLoaders = function (options) {
    options = options || {};

    function generateLoaders(loaders) {
        const sourceLoader = loaders.map(function (loader) {
            if (loader === 'autoprefixer') {
                return {
                    loader: 'postcss-loader',
                    options: {
                        sourceMap: options.sourceMap,
                        plugins: function () {
                            return [autoprefixer()]
                        }
                    }
                }
            }
            var extraParamChar;
            if (/\?/.test(loader)) {
                loader = loader.replace(/\?/, '-loader?');
                extraParamChar = '&'
            } else {
                loader = loader + '-loader';
                extraParamChar = '?'
            }

            return loader + (options.sourceMap ? extraParamChar + 'sourceMap' : '');
        });

        if (options.extract) {
            return ExtractTextPlugin.extract({fallback: 'vue-style-loader', use: sourceLoader});
        } else {
            return ['vue-style-loader'].concat(sourceLoader);
        }
    }

    return {
        css: generateLoaders(['css', 'autoprefixer']),
        scss: generateLoaders(['css', 'autoprefixer', 'sass'])
    }
};

exports.styleLoaders = function (options) {
    const loaders = exports.cssLoaders(options);

    const output = [];
    for (const extension in loaders) {
        const loader = loaders[extension];
        output.push({
            test: new RegExp('\\.' + extension + '$'),
            loader: loader
        });
    }

    return output;
};
