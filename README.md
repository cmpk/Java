# Java
ビジネスロジックに依存しない、基本的な機能の使い方調査のためのディレクトリ

## 前提条件
* IDE には eclipse (日本語) を利用
* Java 8 を使用
* 依存ライブラリや依存プロジェクトは gradle を使用して解決する
* Windows での開発と実行を前提とし、以下とする  
  * 改行コードはCrLf
* 商用理由含めて幅広く使えるコードを書きたいため、ライブラリは組合わせソースコードの公開義務がないものを利用する

## 一覧

| ディレクトリ | 内容                                         | 依存プロジェクト              | 主要依存ライブラリ, 参考サイト | 備考  |
| ------------ | -------------------------------------------- | ----------------------------- | ------------------------------ | ----- |
| command      | 外部コマンド実行                             | -                             | [Apache Commons Lang](https://commons.apache.org/proper/commons-lang/) | 単独では実行不可。他プロジェクトから利用することを前提としている。 |
| csv          | CSVの読込と書込み <br> 各値のバリデーション  | -                             | <ul><li>[Apache Commons CSV](https://commons.apache.org/proper/commons-csv/)</li><li>[Bean Validation](https://beanvalidation.org/)</li></ul> ||
| db           | DB（Oracle）操作                             | command <br> properties       | -                              ||
| ftp          | FTP GET                                      | -                             | [Apache Commons Net](https://commons.apache.org/proper/commons-net/)||
| javamail     | メール内容作成にテンプレートを使用してメール送信 | properties                | <ul><li>[Java Mail](https://javaee.github.io/javamail/)</li><li>[Apache FreeMaker](https://freemarker.apache.org/)</li></ul> ||
| log4j2       | ロギングの仕方                               | -                             | [Apache Log4j 2](https://logging.apache.org/log4j/2.x/) ||
| properties   | プロパティファイルの読込                     | -                             | -                              ||
| sharedfolders| Windows 共有フォルダへのアクセス             | properties                    | -                              ||

## checkstyle について

* 可読性を上げるため、あえてマジックナンバーを使用したい部分に対する警告を抑制するよう、以下を実行している
  * 以下を有効化
    * フィルター > Suppress With Nearby Comment Filter
    * フィルター > Suppression With Plain Text Comment Filter
  * お世話になったサイト
    * [敢えて規約を破るケース（Checkstyleの警告抑制）](http://daisuke-m.hatenablog.com/entry/20090914/1252946741)
* UTは適用対象外としている
  * File Filters > Before Execution Exclusion File Filter で fileNamePatter に ".*Test.java" を指定

## Eclipse で Gradle を使用する

2019年9月時点の eclipse を利用する場合は、プリインストールされているものを使用する。  

インストールされていない場合は、以下を実行する。  

1. JDKをインストール
1. eclipse（日本語版） Java Full Edition を Windows 10にダウンロード & 任意の場所に解凍  
  ・Full Edition でなくてもいいかも  
  ・ダウンロード元は[こちら、Pleiades All in One ダウンロード](https://mergedoc.osdn.jp/)  
  ・Windows標準機能だとファイル名が長すぎて解凍できないらしい（7zipとか使う）
1. eclipse を起動し、メニュー[新規ソフトウェアのインストール]
1. 以下を入力して"Buildship: Gradle 用 Eclipse プラグイン"をインストール  
  作業対象： http://download.eclipse.org/releases/photon   
  フィルター：buildship  
1. メニュー[ファイル] > [新規] > [Gradle プロジェクト] で新規プロジェクトを作成  
1. サンプルとしてlog4j 2を指定してみる  
build.gradle に [Using Log4j in your Gradle build](https://logging.apache.org/log4j/2.x/maven-artifacts.html) に指定されている内容を記載する

## License
ここで公開しているコードはどこでどんな風に使っていただいても構いません。  
ただし、コード利用で発生する事項に対する責任は一切とらないよ！  
（UTが不十分なのもあり、あくまでも参考程度に。）  

依存ライブラリのライセンスは各ライブラリのライセンスに従ってください。  

## 参考サイト
* [EclipseのGradleプロジェクト作成方法比較 方法４：Eclipse公式のBuildshipプラグインで、Eclipse上からプロジェクトを作成](https://qiita.com/grachro/items/d1ebad3857a794895426#%E6%96%B9%E6%B3%95%EF%BC%94eclipse%E5%85%AC%E5%BC%8F%E3%81%AEbuildship%E3%83%97%E3%83%A9%E3%82%B0%E3%82%A4%E3%83%B3%E3%81%A7eclipse%E4%B8%8A%E3%81%8B%E3%82%89%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%82%92%E4%BD%9C%E6%88%90)
* [開発では3つのOSSライセンス分類で対応を判断する](https://emgr.jp/3-oss-license-categories/#toc4)
