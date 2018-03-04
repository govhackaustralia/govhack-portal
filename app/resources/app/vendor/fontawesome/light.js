(function () {
    'use strict';

    var _WINDOW = {};
    try {
        if (typeof window !== 'undefined') _WINDOW = window;

    } catch (e) {
    }

    var _ref = _WINDOW.navigator || {};
    var _ref$userAgent = _ref.userAgent;
    var userAgent = _ref$userAgent === undefined ? '' : _ref$userAgent;

    var WINDOW = _WINDOW;


    var IS_IE = ~userAgent.indexOf('MSIE') || ~userAgent.indexOf('Trident/');

    var NAMESPACE_IDENTIFIER = '___FONT_AWESOME___';


    var oneToTen = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    var oneToTwenty = oneToTen.concat([11, 12, 13, 14, 15, 16, 17, 18, 19, 20]);


    var RESERVED_CLASSES = ['xs', 'sm', 'lg', 'fw', 'ul', 'li', 'border', 'pull-left', 'pull-right', 'spin', 'pulse', 'rotate-90', 'rotate-180', 'rotate-270', 'flip-horizontal', 'flip-vertical', 'stack', 'stack-1x', 'stack-2x', 'inverse', 'layers', 'layers-text', 'layers-counter'].concat(oneToTen.map(function (n) {
        return n + 'x';
    })).concat(oneToTwenty.map(function (n) {
        return 'w-' + n;
    }));

    function bunker(fn) {
        try {
            fn();
        } catch (e) {
            if (undefined !== 'production') {
                throw e;
            }
        }
    }

    var icons$1 = {
        "star": [576, 512, [], "f005", "M528.1 171.5L382 150.2 316.7 17.8c-11.7-23.6-45.6-23.9-57.4 0L194 150.2 47.9 171.5c-26.2 3.8-36.7 36.1-17.7 54.6l105.7 103-25 145.5c-4.5 26.3 23.2 46 46.4 33.7L288 439.6l130.7 68.7c23.2 12.2 50.9-7.4 46.4-33.7l-25-145.5 105.7-103c19-18.5 8.5-50.8-17.7-54.6zM405.8 317.9l27.8 162L288 403.5 142.5 480l27.8-162L52.5 203.1l162.7-23.6L288 32l72.8 147.5 162.7 23.6-117.7 114.8z"],
    };

    var w = WINDOW || {};

    if (!w[NAMESPACE_IDENTIFIER]) w[NAMESPACE_IDENTIFIER] = {};
    if (!w[NAMESPACE_IDENTIFIER].packs) w[NAMESPACE_IDENTIFIER].packs = {};
    if (!w[NAMESPACE_IDENTIFIER].hooks) w[NAMESPACE_IDENTIFIER].hooks = {};
    if (!w[NAMESPACE_IDENTIFIER].shims) w[NAMESPACE_IDENTIFIER].shims = [];

    var namespace = w[NAMESPACE_IDENTIFIER];

    var babelHelpers = {};


    var _extends = Object.assign || function (target) {
        for (var i = 1; i < arguments.length; i++) {
            var source = arguments[i];

            for (var key in source) {
                if (Object.prototype.hasOwnProperty.call(source, key)) {
                    target[key] = source[key];
                }
            }
        }

        return target;
    };


    babelHelpers;

    function define(prefix) {
        if (typeof namespace.hooks.addPack === 'function') {
            namespace.hooks.addPack(prefix, icons$1);
        } else {
            namespace.packs[prefix] = _extends({}, namespace.packs[prefix] || {}, icons$1);
        }
    }

    bunker(function () {
        define('fal');
    });

}());
