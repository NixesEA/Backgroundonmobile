package ru.pushapp.backgroungonmobile;

public class ImageModel {
    String url;
    String category;
    int countLikes;
    int countDownload;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCountLikes() {
        return countLikes;
    }

    public void setCountLikes(int countLikes) {
        this.countLikes = countLikes;
    }

    public int getCountDownload() {
        return countDownload;
    }

    public void setCountDownload(int countDownload) {
        this.countDownload = countDownload;
    }
}
