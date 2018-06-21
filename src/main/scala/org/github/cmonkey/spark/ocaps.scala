object Ocaps extends App{
  case class Foo(name: String)

  def createFoo: Unit = Foo("will")

  def createFoo2: Foo = Foo("will")

  val foo = createFoo2
  val name = foo.name
  println(s"$name")

  trait Cake {
    def eat(): Unit 
  }

  val cake = new Cake(){
    def eat() = ()
  }

  cake.eat()

  trait Bakery{
    def buy(): Cake
  }

  class Person(val bakery: Bakery){
    def buyAndEatCake()= eatCake(bakery.buy())
    def eatCake(cake: Cake) = cake.eat()
  }

  val bakery = new Bakery(){
    def buy(): Cake = new Cake(){
      def eat() = ()
    }
  }

  val you = new Person(bakery)
  you.buyAndEatCake()

  val enforcingBakery = new Bakery(){
    def buy(): Cake = {
      val latch = new java.util.concurrent.atomic.AtomicInteger(1)
      val realCake = new Cake(){
        def eat() = ()
      }

      new Cake(){
        def eat() = {
          if(latch.getAndDecrement == 1){
            realCake.eat()
          }else{
            throw new RuntimeException("You have already eaten this cake!")
          }
        }
      }
    }
  }

  val snekyYou = new Person(enforcingBakery)
  val oneTimeCake = snekyYou.bakery.buy()
  snekyYou.eatCake(oneTimeCake)
  snekyYou.eatCake(oneTimeCake)


}
