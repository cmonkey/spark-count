package org.github.cmonkey.spark.timeWheel
import java.util
import java.util.concurrent._
import java.util.concurrent.atomic.AtomicLong

class TimeWheel extends AbstractScheduler{

  val dialInSeconds = new java.util.ArrayList[Slot[AbstractTask]](60)
  val dialInMinutes = new util.ArrayList[Slot[AbstractTask]](60)
  val didalInHours = new util.ArrayList[Slot[AbstractTask]](24)
  val dialInDays = new util.ArrayList[Slot[AbstractTask]](30)

  val timeSecondDriver: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
  val timeMinuteDriver: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
  val timeHourDriver: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
  val timeDayDriver: ScheduledExecutorService = Executors.newScheduledThreadPool(1)

  var secondDriver: ScheduledExecutorService = null
  val minuteDriver: ScheduledExecutorService = null
  val hourDriver: ScheduledExecutorService = null
  val dayDriver: ScheduledExecutorService = null

  var workThreads: ThreadPoolExecutor = null
  val DEFAULT_WORK_THREADS = 10
  @volatile var isRunning: Boolean = false
  var workProducerThread: ExecutorService  = null
  var workConsumerThread: ExecutorService  = null

  import org.github.cmonkey.spark.timeWheel.AbstractTask
  import java.util.concurrent.BlockingDeque
  import java.util.concurrent.LinkedBlockingDeque

  val tasks = new LinkedBlockingDeque[AbstractTask]

  override def build(): TimeWheel = {

    for (i <- 0 to 60){
      dialInSeconds.add(new Slot[AbstractTask]())
    }

    workThreads = Executors.newFixedThreadPool(DEFAULT_WORK_THREADS, new ThreadFactory {
      val count = new AtomicLong(0)
      override def newThread(r: Runnable): Thread = {
        val t = new Thread(new ThreadGroup("TimeWheel"), r , "work-" + count.getAndIncrement())
        t
      }
    }).asInstanceOf[ThreadPoolExecutor]

    secondDriver = Executors.newScheduledThreadPool(1, new ThreadFactory {
      override def newThread(r: Runnable): Thread ={
        val t = new Thread(new ThreadGroup("TimeWheel"), r, "secondDriver")
        t
      }
    })

    this
  }

  override def start(): Boolean = {

    if (null == secondDriver){
      throw new IllegalStateException("secondDriver is not created")
    }

    if(!isRunning){

      // 1000 毫秒执行一次秒级任务
      timeSecondDriver.scheduleAtFixedRate(new TimeDriverThread(secondDriver, new WorkProducer(1, dialInSeconds.get(1))), 0, 1000, TimeUnit.MILLISECONDS)
      isRunning = true

      workProducerThread = Executors.newSingleThreadExecutor(new ThreadFactory {
        override def newThread(r: Runnable): Thread = {
          val t = new Thread(new ThreadGroup("TimeWheel"), r, "workProducer")
          t
        }
      })

      workConsumerThread = Executors.newSingleThreadExecutor(new ThreadFactory {
        override def newThread(r: Runnable): Thread = {
          val t = new Thread(new ThreadGroup("TimeWheel"), r, "workConsumer")
          t
        }
      })
    }

    isRunning
  }

  override def stop(): Boolean ={
   if(isRunning) {
     isRunning = false
     secondDriver.shutdown()
     workThreads.shutdown()
     workProducerThread.shutdown()
     workConsumerThread.shutdown()
   }

    !isRunning
  }

  def produceLogWork(pointer: Int) = {
    val slot = dialInSeconds.get(pointer)
  }
  def init() = {
    // 60 秒执行一次分钟级别任务
    //timeMinuteDriver.scheduleAtFixedRate(minuteDriver, 0, 60, TimeUnit.SECONDS)
    // 60 分钟执行一次小时级别任务
    //timeHourDriver.scheduleAtFixedRate(hourDriver, 0, 60, TimeUnit.MINUTES)
    // 24 小时执行一次天级任务
    //timeDayDriver.scheduleAtFixedRate(dayDriver, 0, 24, TimeUnit.HOURS)
  }

  override def destroy(): Unit = {

  }

  override def unregistTask(abstractTask: AbstractTask): Unit = {

  }

}
