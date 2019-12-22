*準備中*  

# 概要  

CSV ファイルを読み込む

# 前提条件  

* CSVファイル読み込み部分は汎用化する  
  * どのようなCSVフォーマットにも対応可能とする  
* CSVの任意の行のカラム数が不正な場合にカラムごとのバリデーションを実行しない
  * お世話になったサイト
    * [Bean ValidationのGroup sequenceは単項目チェック、相関チェックの順序指定で使うのは止めた方が良さそう](https://qiita.com/eiryu/items/95a206d617bd2b956953)

# 課題  

* UT作成  
  * カスタムバリデーションがあるのでさすがにUTは必要っしょ  
* 何行目のエラーか表示する  

# メッセージファイルについて

* ValidationMessages.properties の文字コードは ISO-8859-1（Latin-1）である必要がある  
  * properties ファイルが Hibernate 内部で ISO-8859-1 で読み込まれる模様  

# checkstyle について

* 可読性を上げるため、あえてマジックナンバーを使用したい部分に対する警告を抑制するよう、以下を実行している
  * 以下を有効化
    * フィルター > Suppress With Nearby Comment Filter
    * フィルター > Suppression With Plain Text Comment Filter
  * お世話になったサイト
    * [敢えて規約を破るケース（Checkstyleの警告抑制）](http://daisuke-m.hatenablog.com/entry/20090914/1252946741)

