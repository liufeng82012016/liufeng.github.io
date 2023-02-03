// 使用ES6模块化导入jquery
import $ from 'jquery';

import './css/index.css';
import './css/index.less';
// 实现各行变色效果
$(function () {
    $('li:odd').css('background-color', 'lightblue');
    $('li:even').css('background-color', 'pink');
})

class Person{
    static info = 'person info'
}

console.log(Person.info);