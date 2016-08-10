/**
 * Created by muhammedpatel on 2016/08/10.
 */
var webpack = require('webpack');
var path = require('path');

module.exports = {
    entry: {
        'polyfills': './lib',
    },
    output: {
        path: "./test",
        filename: "bundle.js"
    },
    module: {
        loaders: [
            { test: /\.css$/, loader: "style!css" }
        ]
    },
    plugins: [
        new webpack.optimize.OccurenceOrderPlugin(true),
        new webpack.optimize.CommonsChunkPlugin({ name: ['main', 'vendor', 'polyfills'], minChunks: Infinity }),
    ],

};