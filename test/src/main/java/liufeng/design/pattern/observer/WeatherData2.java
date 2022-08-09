package liufeng.design.pattern.observer;

import org.junit.Test;

import java.util.Observable;
import java.util.Observer;

/**
 * 天气气象站
 * 由WeatherData追踪目前的天气状况（温度、湿度、气压）
 * 另外建立一个应用，有3种布告板，分别显示当前的状况、气象统计及简单的预报。当WeatherData更新时，三种布告板必须即时更新
 * 而且这是可扩展的气象站，可公布一组api，让其他的开发人员可以写出自己的气象布告板
 */
public class WeatherData2 extends Observable {

    private float temperature;
    private float humidity;
    private float pressure;

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementChanged();
    }


    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    /**
     * 当气象更新时，调用此方法
     */
    public void measurementChanged() {
        // v1：更新3个布告板
        // currentConditionDisplay.update(...);
        // statisticsDisplay.update(...);
        // forecastDisplay.update(...);
        // v2：WeatherData implements Subject
        // currentConditionDisplay/forecastDisplay/forecastDisplay注册为WeatherData的观察者
        notifyObservers();
    }


    @Test
    public void run() {
        WeatherData2 weatherData = new WeatherData2();
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);

        weatherData.setMeasurements(60, 65, 30.4f);
        // 先标记为已修改
        weatherData.setChanged();
        weatherData.notifyObservers();
        // 重载方法，可以传任意参数给观察者
        // weatherData.notifyObservers(null);
    }


    interface DisplayElement2 {
        public void display();
    }

    class CurrentConditionDisplay implements Observer, DisplayElement2 {
        private float temperature;
        private float humidity;
        private float pressure;
        private Observable observable;

        public CurrentConditionDisplay(Observable observable) {
            observable.addObserver(this);
            this.observable = observable;
        }


        @Override
        public void display() {
            System.out.println(this);
        }

        @Override
        public String toString() {
            return "CurrentConditionDisplay{" +
                    "temperature=" + temperature +
                    ", humidity=" + humidity +
                    ", pressure=" + pressure +
                    '}';
        }

        @Override
        public void update(Observable o, Object arg) {
            if (o instanceof WeatherData2) {
                WeatherData2 weatherData = (WeatherData2) o;
                this.temperature = weatherData.getTemperature();
                this.humidity = weatherData.getHumidity();
                this.pressure = weatherData.getPressure();
            }
            display();
        }
    }


}






