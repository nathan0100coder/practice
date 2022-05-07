package practice.applications.utils;

/**
 * @author shiLong
 * @version 1.0
 * @desc 文件名后缀枚举
 * @date 2021/10/14
 */
public enum FileSuffix {
    JPG(1, ".jpg"), JPEG(2, ".jpeg"), PNG(3, ".png"),BMP(4, ".bmp")
    ,MP3(5, ".mp3"),MP4(6, ".mp4"),MKV(7, ".mkv"),AVI(8, ".avi"),MOV(9, ".mov");
    private final Integer number;
    private final String suffix;

    FileSuffix(Integer number, String suffix) {
        this.number = number;
        this.suffix = suffix;
    }

    public Integer getNumber() {
        return number;
    }

//    public void setNumber(Integer number) {
//        this.number = number;
//    }

    public String getSuffix() {
        return suffix;
    }

//    public void setSuffix(String suffix) {
//        this.suffix = suffix;
//    }
}
