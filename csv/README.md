# 概要  

CSV ファイルの読み込み、値のバリデート、書き込みを行う

# 前提条件  

* CSVファイル読み込み部分は汎用化する  
  * どのようなCSVフォーマットにも対応可能とする  
* CSVの任意の行のカラム数が不正な場合にカラムごとのバリデーションを実行しない
  * お世話になったサイト
    * [Bean ValidationのGroup sequenceは単項目チェック、相関チェックの順序指定で使うのは止めた方が良さそう](https://qiita.com/eiryu/items/95a206d617bd2b956953)

# メッセージファイルについて

* ValidationMessages.properties の文字コードは ISO-8859-1（Latin-1）である必要がある  
  * properties ファイルが Hibernate 内部で ISO-8859-1 で読み込まれる模様  

