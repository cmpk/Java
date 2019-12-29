# CSV ファイルの読み込み、値のバリデート、書き込み方法調査

## 目的  
* CSV ファイルを読み込んで Bean 的なオブジェクトにする方法を調査する  
* CSV ファイルから読み込んだ内容のフォーマットチェックを実行する方法を調査する  
* Bean 的なオブジェクトを CSV ファイルに書き込む方法を調査する

## 前提条件  

* CSVファイル読み込み部分は汎用化する  
  * どのようなCSVフォーマットにも対応可能とする  
* CSVの任意の行のカラム数が不正な場合にカラムごとのバリデーションを実行しない
  * お世話になったサイト
    * [Bean Validationで簡単入力チェック！](https://qiita.com/5zm/items/89b7198cab74f2d0f4a1)
    * [Bean ValidationのGroup sequenceは単項目チェック、相関チェックの順序指定で使うのは止めた方が良さそう](https://qiita.com/eiryu/items/95a206d617bd2b956953)

## メッセージファイルについて

* ValidationMessages.properties の文字コードは ISO-8859-1（Latin-1）である必要がある  
  * properties ファイルが Hibernate 内部で ISO-8859-1 で読み込まれる模様  

## 課題

* UT を充実させる  

