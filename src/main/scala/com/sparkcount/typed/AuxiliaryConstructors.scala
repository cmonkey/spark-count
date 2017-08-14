package com.sparkcount.typed

class AuxiliaryConstructors (name:String, age: Int){

  def this(){
    this("", 1)
  }

  def this(name:String){
    this(name, -1)
  }

  def this(age: Int){
    this("", age)
  }

  def getName(): String ={
    name
  }

  def getAge(): Int = {
    age
  }

}
