# log4j 2 利用方法調査

## 目的
[Apache log4j 2](https://logging.apache.org/log4j/2.x/) の使用方法を調査する。  

## 前提条件

* Windows で動作させるためソースコード、ログファイルともに SJIS とする  

## 課題

* 実行時間の測定はAOPで書きたいなぁ
  * log4j の設定が面倒なので、Spring Boot は使いたくない
  * aspectJ は Gradle 5 ではバグのためにコンパイルできないっぽい

## TODO

* UT を書く

## 気づいたこと

ログファイルをパッケージ毎に分けたい場合、パッケージ毎にlog4j 2設定ファイルに＜Logger＞を指定する。  
この時、ロガーインスタンスもパッケージ毎に生成する必要がある。  
```
protected final Logger logger = LogManager.getLogger();
```

最初、ログ出力したいクラスはすべて以下の共通抽象クラスを extends するよう実装したら、log4j 2設定ファイルの＜Root＞に指定した設定しか有効にならなかった。
```
package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/***
 * ロギング共通実装.
 */
public abstract class BaseLogger {
    protected final Logger logger = LogManager.getLogger();
}
```

## お世話になったサイト
[今さら聞けないJAVAログ出力 LOG4J入門](https://mashpote.net/2017/05/17/%E4%BB%8A%E3%81%95%E3%82%89%E8%81%9E%E3%81%91%E3%81%AA%E3%81%84java%E3%83%AD%E3%82%B0%E5%87%BA%E5%8A%9B-log4j%E5%85%A5%E9%96%80/)
