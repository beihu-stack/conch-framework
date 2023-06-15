package ltd.beihu.akka.demos.event;

import akka.actor.typed.Behavior;
import akka.actor.typed.PostStop;
import akka.actor.typed.PreRestart;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

/**
 * Event3 事件 处理行为定义
 *
 * @author Adam
 * @date 2021/2/23
 */
public class Event3Behavior extends AbstractBehavior<Event3> {

    /**
     * 最大处理次数
     */
    private final int max;
    /**
     * 处理次数累加
     */
    private int eventCounter;

    public Event3Behavior(ActorContext<Event3> context, int max) {
        super(context);
        this.max = max;
    }

    public static Behavior<Event3> create(int max) {
        return Behaviors.setup(context -> new Event3Behavior(context, max));
    }

    @Override
    public Receive<Event3> createReceive() {
        return newReceiveBuilder().onMessage(Event3.class, this::handleEvent3)
                .onSignal(PreRestart.class, signal -> preRestart())
                .onSignal(PostStop.class, signal -> onPostStop())
                .build();
    }

    /**
     * 处理预重启信号 - 生命周期
     *
     * @return Behavior Event3
     */
    private Behavior<Event3> preRestart() {
        getContext().getLog().info("Event3Behavior will be restarted");
        return this;
    }

    /**
     * 处理停止信号 - 生命周期
     *
     * @return Behavior Event3
     */
    private Behavior<Event3> onPostStop() {
        getContext().getLog().info("Event3Behavior Stopped");
        return this;
    }

    /**
     * 处理 event3
     *
     * @param event3 event3
     * @return Behavior Event3
     */
    public Behavior<Event3> handleEvent3(Event3 event3) {
        eventCounter++;
        getContext().getLog().info("Greeting {} for {}", eventCounter, event3.whom);
        if (eventCounter == max) {
            // 停止
            return Behaviors.stopped();
        } else if (eventCounter == 2) {
            // 抛异常 - 重启 Event3Behavior - todo 发现 Event3Behavior 重启后 并没有记录继续执行当前任务
            getContext().getLog().error("Event3Behavior actor fails now");
            throw new RuntimeException("I failed!");
        } else {
            // 再次发送Event2事件
            event3.event2ActorRef.tell(new Event2(event3.whom, getContext().getSelf()));
            return this;
        }
    }
}
