# Java
ビジネスロジックに依存しない、基本的な機能の使い方調査のためのディレクトリ

## 前提条件
* IDE には eclipse (日本語) を利用
* Java 8
* 基本的な実装は、ビジネスロジックを実装するプロジェクトとは別のプロジェクトとする。
∵ログ出力はアプリケーションの基本的な機能となるため
* 依存ライブラリや依存プロジェクトは gradle を使用して解決する
* 設定ファイルはSJISで記載

## 一覧

| ディレクトリ | 内容 |
| ------------ | ---- |
| log4j2       | ロギングの仕方を調査 |
| javamail     | メール内容作成にテンプレート（freemarker）を使用してメールを送信する方法を調査 |


## Eclipse で Gradle を使用する

2019年9月時点の eclipse を利用する場合は、プリインストールされている。  

### 手順
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

### 参考サイト
* [EclipseのGradleプロジェクト作成方法比較 方法４：Eclipse公式のBuildshipプラグインで、Eclipse上からプロジェクトを作成](https://qiita.com/grachro/items/d1ebad3857a794895426#%E6%96%B9%E6%B3%95%EF%BC%94eclipse%E5%85%AC%E5%BC%8F%E3%81%AEbuildship%E3%83%97%E3%83%A9%E3%82%B0%E3%82%A4%E3%83%B3%E3%81%A7eclipse%E4%B8%8A%E3%81%8B%E3%82%89%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%82%92%E4%BD%9C%E6%88%90)
