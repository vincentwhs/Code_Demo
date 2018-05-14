function Person(name){
    this.name=name;
    this.setName = function (n) {
        this.name  = n;
    }
    this.getName= function () {
        return this.name;
    }
}

var p = new Person("lisi");


function Student(){}
Student.prototype = p;
var stu = new Student();

//stu.prototype = p;

//stu.setName("wangwu");
//alert(stu.getName());

Person.prototype.show = function () {
    alert("hello");
}