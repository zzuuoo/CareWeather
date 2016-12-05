package com.yezi.zuo.careweather.Activity;

import java.util.List;

/**
 * Created by zuo on 2016/12/5.
 */

/*
{
        "error": 0,
        "status": "success",
        "date": "2016-12-05",
        "results": [
        {
        "currentCity": "西安",
        "pm25": "94",
        "index": [
        {
        "title": "穿衣",
        "zs": "较冷",
        "tipt": "穿衣指数",
        "des": "建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"
        },
        {
        "title": "洗车",
        "zs": "较适宜",
        "tipt": "洗车指数",
        "des": "较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"
        },
        {
        "title": "旅游",
        "zs": "适宜",
        "tipt": "旅游指数",
        "des": "天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。"
        },
        {
        "title": "感冒",
        "zs": "易发",
        "tipt": "感冒指数",
        "des": "昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。"
        },
        {
        "title": "运动",
        "zs": "较不宜",
        "tipt": "运动指数",
        "des": "天气较好，但考虑天气寒冷，推荐您进行各种室内运动，若在户外运动请注意保暖并做好准备活动。"
        },
        {
        "title": "紫外线强度",
        "zs": "中等",
        "tipt": "紫外线强度指数",
        "des": "属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"
        }
        ],
        "weather_data": [
        {
        "date": "周一 12月05日 (实时：13℃)",
        "dayPictureUrl": "http://api.map.baidu.com/images/weather/day/qing.png",
        "nightPictureUrl": "http://api.map.baidu.com/images/weather/night/qing.png",
        "weather": "晴",
        "wind": "东北风微风",
        "temperature": "13 ~ 0℃"
        },
        {
        "date": "周二",
        "dayPictureUrl": "http://api.map.baidu.com/images/weather/day/qing.png",
        "nightPictureUrl": "http://api.map.baidu.com/images/weather/night/qing.png",
        "weather": "晴",
        "wind": "东北风微风",
        "temperature": "12 ~ -1℃"
        },
        {
        "date": "周三",
        "dayPictureUrl": "http://api.map.baidu.com/images/weather/day/duoyun.png",
        "nightPictureUrl": "http://api.map.baidu.com/images/weather/night/qing.png",
        "weather": "多云转晴",
        "wind": "东北风微风",
        "temperature": "9 ~ 1℃"
        },
        {
        "date": "周四",
        "dayPictureUrl": "http://api.map.baidu.com/images/weather/day/duoyun.png",
        "nightPictureUrl": "http://api.map.baidu.com/images/weather/night/duoyun.png",
        "weather": "多云",
        "wind": "东北风微风",
        "temperature": "12 ~ 2℃"
        }
        ]
        }
        ]
        }

*/

public class Weather {
    private int error;
    private String status;
    private String date;
    private List<Results> results;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }
}
class Weather_data{
    private String date;
    private String dayPictureUrl;
    private String nightPictureUrl;
    private String weather;
    private String wind;
    private String temperature;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayPictureUrl() {
        return dayPictureUrl;
    }

    public void setDayPictureUrl(String dayPictureUrl) {
        this.dayPictureUrl = dayPictureUrl;
    }

    public String getNightPictureUrl() {
        return nightPictureUrl;
    }

    public void setNightPictureUrl(String nightPictureUrl) {
        this.nightPictureUrl = nightPictureUrl;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}

class Results{
    private String currentCity;
    private String pm25;
    private List<Index> index;
    private List<Weather_data> weather_data;

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public List<Index> getIndex() {
        return index;
    }

    public void setIndex(List<Index> index) {
        this.index = index;
    }

    public List<Weather_data> getWeather_data() {
        return weather_data;
    }

    public void setWeather_data(List<Weather_data> weather_data) {
        this.weather_data = weather_data;
    }
}
class Index{
    private String title;
    private String zs;
    private String tipt;
    private String des;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    public String getTipt() {
        return tipt;
    }

    public void setTipt(String tipt) {
        this.tipt = tipt;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }


}

