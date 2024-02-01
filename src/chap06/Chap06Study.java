package chap06;

public class Chap06Study {

}

/*
「テンプレートエンジン」とはデータとあらかじめ定義されたテンプレートを
バインド（=何らかの要素やデータ、ファイルなどを相互に関連付ける）して、ビューの表示を助けるものである。
また、「Thymeleaf（タイムリーフ）」とはテンプレートエンジンの1つで、Spring Bootで使用が推奨されている。
特徴としては、
・HTMLベースのテンプレートエンジンで、決められた式を記述することで、ページを動的に組み立ててくれる。
・HTMLがベースとなるため、最終的な出力をイメージしながらビューを作成できる（デザイナーとの分業が簡単になる）。


○リクエスト受信からレスポンス送信までの流れ
1.DispatcherServletがクライアントからのリクエストを受け取る（フロントコントローラパターン）
2.DispatcherServletがコントローラのリクエストハンドラメソッドを呼び出す
3.コントローラはサービス処理を実行し、処理結果を取得する
4.処理結果をModelに設定し、ビュー名を返却する
5.DispatcherServletは、ビュー名に対応するビューに対して画面表示処理を依頼する
6.クライアントがレスポンスを受け取り、ブラウザに画面が表示される


Spring MVCが提供するModelインターフェースの特徴は以下の3点である。
・処理したデータをビューに表示させたい場合、データを引き渡す役割を持っている。
・Spring MVCにより管理され、手動または自動でオブジェクトを格納し管理する機能を提供する。
・Modelを利用したい場合、リクエストハンドラメソッドの引数にModel型を渡す。Spring MVCが自動的にModel型のインスタンスを設定する。


Modelにオブジェクトを格納するためのメソッドの1つとして「addAttribute」があり、指定された名前に対して、指定された値を設定する。
Model addAttribute(String name, Object value)
name:名前（ニックネームのようなイメージ）
value:値（格納したいオブジェクト）


○Modelを使ったプログラムの作成
1.プロジェクトの作成
「ファイル」→「新規」→「Springスターター・プロジェクト」を選択し、「新規Springスターター・プロジェクト」画面で作成する。
「依存関係」画面では「Spring Boot DevTools(開発ツール)」、「Thymeleaf(テンプレートエンジン)」、「Spring Web(Web)」を選択する。
2.コントローラの作成
「src/main/java」→「com.example.demo」フォルダを右クリックし「新規」→「クラス」を選択する。
3.ビューの作成
「src/main/resouces」→「templates」フォルダを右クリックし「新規」→「その他」を選択する。
「HTMLファイル」を選択し、ファイル名を入力する。
HTMLファイル内にThymeleafを使用することを宣言しThymeleafの機能は「th:xxx属性名」の形式で埋め込む。
|属性    |機能概要                                          |
|th:text |属性値に指定された値をサニタイズして出力する      |
|th:utext|属性値に指定された値を出力する（サニタイズしない）|
※「サニタイズ」とは、危険なコードやデータを変換または除去して無力化する処理である。
4.実行と確認
HTMLファイルを右クリックし「次で開く」→「Webブラウザー」を選択する。（これはアプリケーションを起動せずファイルの内容を表す）
「ウインドウ」→「ビューの表示」→「その他」を選択し「ビューの表示」画面で「Bootダッシュボード」を選択し、開く。
「Bootダッシュボード」上で自身で作製したプロジェクトを選択し「起動」ボタンを押す。（アプリケーションを起動して確認する）


「Thymeleaf」の特徴は以下の3点である。
・Spring Bootで使用が推奨されているテンプレートエンジンである。
・HTMLとしてテンプレートを記述することができるため、Webブラウザでファイルの内容を表示させ、確認しながらビューを作成できる。
・Spring Bootのデフォルト設定では、src/main/resources/templatesフォルダ配下のリクエストハンドラメソッド+.htmlファイルが参照される。


○Thymeleafの使用方法
・直接文字を埋め込む
「th:text="[出力文字]"」とすることで、設定した文字を出力できる。また、[出力文字]の部分はThymeleafの独自式「${}」が使用できる。
<h1 th:text="hello world">表示される部分</h1>

・インライン処理
「[[${}]]」を使用すると、タグの属性への追加ではなく本体へ変数を埋め込む。固定値と変数を組み合わせたい場合はこちらが良い。
<h1>こんにちは[[${name}]]さん</h1>

・値結合
「+」を利用して値の連結ができる。
<h1 th:text="'明日は、' + '晴れ' + 'です。'">表示する部分</h1>

・値結合（リテラル置換）
「値結合」はリテラル置換を使用することで、"|文字|"で記述ができる。文字の中で「${}」も合わせて使用できる。
<h1 th:text="|こんにちは！${name}さん|">表示される部分</h1>

・ローカル変数
「th:with="変数名=値"」で変数に値を代入できる。変数のスコープ（=変数を使用できる有効範囲）は定義されたタグ内部のみ使用できる。
また、「+」,「-」,「*」,「/」,「%」が使用できる。
<div th:with="a=1,b=2">
  <span th:text="|${a} + ${b} = ${a+b}|"></span>
</div>
*/