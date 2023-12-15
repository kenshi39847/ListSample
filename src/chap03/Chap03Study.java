package chap03;

public class Chap03Study {

}

/*
「Spring Framework」はJava開発におけるフレームワークである。単に「Spring」とも呼ばれる。
以下の3つはSpringが提供する機能の一部である。
○Spring Boot
Springアプリケーションを煩雑な設定をせずに迅速に作成する機能
○Spring プロジェクト
・Spring MVC
Webアプリケーションを簡単に作成する機能
・Spring Date
データアクセスに対する機能
・Spring Batch
バッチ処理機能
・Spring Security
認証/認可の機能
○Spring コア
・Spring DI
依存性注入の機能
・Spring AOP
アスペクト指向プログラミングの機能


Spring Frameworkのコアとなる1つ目の機能として「Dependency Injection(DI)：依存性の注入」がある。
プログラムには[使う側]と[使われる側]という関係があり、使いたい機能を呼び出すには[使う側]クラスで[使われる]クラスに対し、
「new」キーワードを使用してインスタンスを生成し参照を取得後、目的の機能を利用する。
もし、[使われる側]クラスが不要になり、別の[使われる側]クラスを利用する場合、[使う側]クラスで
[新たに使われる側]のクラス名およびメソッド名に書き換える必要がある。この書き換える箇所を「依存性がある」という。
依存には以下の2種類がある。
○クラス依存（実装依存）
○インタフェース依存


クラスA[使う側]でnewキーワードを使用しクラスB[使われる側]インスタンスを生成し、インスタンスからメソッド(methodX)を呼び出すコードがある。
（クラスA）
xxx() {
  B b = new B();
  b.methodX();
}
（クラスB）
methodX() {
  ...
}
仕様変更があり、新たに作成されたクラスC[新たに使われる側]のメソッド(methodY)を呼び出すように変更するコードを作る。
（クラスA）
xxx() {
  C c = new C();
  c.methodY();
}
（クラスC）
methodY() {
  ...
}
このように、[使う側]クラスで[使われる側]クラスの型を直接記述してしまうと、
「使われる側」クラスを変更する場合、利用している箇所をすべて修正しなければならない。
この変更箇所が多いことを「高依存」という。


Javaにはクラスに含まれるメソッドの具体的な処理内容は記載せずに、定数とメソッドの型のみを定義した「インターフェース」がある。
インターフェースは他クラスで実装することを前提に作られているため、インターフェースで宣言したメソッドは、
暗黙的に「public abstract」アクセス修飾子がつき、定数になる。
○インターフェース宣言
public interface Greet {
  void greeting()
}


インターフェースIがあり、それを実装したクラスB[使われる側]がある。クラスA[使う側]でnewキーワードを使用して、
クラスB[使われる側]インスタンスを生成し、インスタンスからメソッド(methodX)を呼び出すコードがある。
（クラスA）
xxx() {
  I i = new B();
  i.methodX();
}
（インターフェースI）
methodX();
（クラスB）
methodX() {
  ...
}
仕様変更があり、新たに作成されたクラスC[新たに使われる側]（インターフェースIを実装）のメソッド(methodX)を呼び出すように変更するコードを作る。
（クラスA）
xxx() {
  I i = new C();
  i.methodX();
}
（インターフェースI）
methodX();
（クラスC）
methodX() {
  ...
}
このように、インターフェースを実装した[使われる側]クラスを変更する場合、以下のメリットがある。
・インタフェースは参照を受け取る型として利用できるため、変数の型名を変更しなくてよい
・インタフェース宣言されたメソッドを利用すれば、クラスが変わってもメソッド名を変更しなくてよい
このメリットから「クラス依存」より「インターフェース依存」を使用することで修正箇所を減らすことができ、
また、この変更箇所が少ないことを「低依存」という。


今まではインスタンス生成にはnewキーワードを使用していたが、それをフレームワークに任せたいとき、
その責務を引き受けてくれるものを「DIコンテナ」という。
Spring Frameworkは任意に実装したクラスをインスタンス化する機能（DIコンテナ機能）を提供する。


DIコンテナにインスタンス生成を任せ、以下のルールを守ることで[使う側]クラスの修正をゼロにすることができる。
1.インターフェースを利用して依存関係を作る
依存している部分にはインターフェースを利用する。
2.インスタンスを明示的に生成しない
インスタンス生成にnewキーワードを利用しない。
3.アノテーションをクラスに付与する
4.Spring Frameworkにインスタンスを生成させる
「アノテーション」とは注釈、注記という意味で使われ、「@xxx」のような形で使用される。
主な目的としては外部ソフトウェアにやってほしいことを伝える。例えば、エラーを出力させたり、プログラムの動作を変更させたりできる。
また、Spring Frameworkは起動時に対象プロジェクトのパッケージをすべてスキャンし、この機能を「コンポーネントスキャン」という。
コンポーネントスキャン後、Spring Frameworkはインスタンス生成アノテーションが付与されているクラスを抽出し、
抽出したクラスをインスタンス化する。さらに、インスタンス生成アノテーションは用途別に以下の4種類が利用できる。
○利用できるインスタンス生成アノテーション
|アノテーション|概要                                                        |レイヤ別使いわけ                                |
|@Controller   |Spring MVCを利用する際のコントローラに付与する              |アプリケーション層のコントローラ                |
|@Service      |トランザクション境界となるドメイン（サービス）機能に付与する|ドメイン層の業務処理                            |
|@Repository   |データベースアクセス（リポジトリ）機能に付与する            |インフラストラクチャ層のデータベースアクセス処理|
|@Component    |上記の用途以外のクラスに付与する                            |                        -                       |
ここで、「レイヤ」とは、「層」という意味で、階層構造になっている各々の層のことをいい、使用する場所のことである。
以下の3つのレイヤは「Domain Driven Design:ドメイン駆動設計(DDD)」で使われるレイヤである。
○ドメイン駆動設計(DDD)のレイヤ
|レイヤ                |概要                                                  |
|アプリケーション層    |クライアントとのデータ入出力を制御する層              |
|ドメイン層            |アプリケーションの中核となる層で、業務処理を提供する層|
|インフラストラクチャ層|データベースへのデータ永続化などを担う層              |
5.インスタンスを利用したい箇所でアノテーションを付与する
Spring Frameworkによって生成されたインスタンスを利用するクラスで、参照を受け取るフィールドを宣言し、
フィールドに「@Autowired」アノテーションを付与する。


Spring Frameworkのコアとなる2つ目の機能として「Aspect Oriented Programming(AOP)：アスペクト指向プログラミング」がある。
AOPではプログラムを以下の2つの要素で構成されていると考える。
1.中心的関心事
実現すべき機能を表すプログラム
2.横断的関心事
本質的ではないが、品質や保守/運用等の観点で必ず必要な機能を表すプログラム（例外処理、トランザクション制御等）
AOPは共通処理等の「横断的関心事」を抽出し、プログラムの様々な箇所で呼び出せるように設定することで、
実現すべき「中心的関心事」のみを記述すれば良いという仕組みである。
つまり、AOP機能を利用することで「中心的関心事」と「横断的関心事」を分離してプログラムを簡単に作成できる。


○AOPの固有用語
|用語       |概要                                                                                                          |
|Advice     |横断的関心事の実装（メソッド）（ログ出力やトランザクション制御等）                                            |
|Aspect     |Adviceをまとめたもの（クラス）                                                                                |
|JoinPoint  |Adviceを中心的関心事に適用するタイミング（メソッドの実行前、実行後等）                                        |
|Pointcut   |Adviceを挿入できる場所（メソッド名がgetで始まるときだけ処理するなどの条件を定義する等）                       |
|Interceptor|処理の制御をインターセプト（横取り）するための仕組み、プログラム（Adviceを中心的関心事に追加したように見せる）|
|Target     |Adviceが挿入される対象                                                                                        |


Spring Frameworkが用意する中心的関心事に適用するAdviceには実行制御内容別に5種類ある。
○Advice5種類
|Advice                |内容                                                                              |アノテーション |
|Before Advice         |中心的関心事が実行される前に横断的関心事を実行する                                |@Before        |
|After Returning Advice|中心的関心事が正常に終了した後で横断的関心事を実行する                            |@AfterReturning|
|After Throwing Advice |中心的関心事で例外がスローされた後で横断的関心事を実行する                        |@AfterThrowing |
|After Advice          |中心的関心事の実行後に横断的関心事を実行する（正常終了・例外終了の有無は問わない）|@After         |
|Around Advice         |中心的関心事の呼び出し前後で横断的関心事を実行する                                |@Around        |


自分でAdviceを作成する場合、パッケージ、クラス、メソッド等、Advice挿入対象を条件で指定できる。
指定する条件方法には「Pointcut式」を使用する。Pointcut式は複数あるが、今回は「execution指示子」を使う。
○execution指示子の構文
execution(戻り値の型 パッケージ.クラス.メソッド(引数))


Pointcut式はワイルドカードを利用することで、柔軟に適用範囲を指定できる。
※ワイルドカードとは特殊文字の種類で、どんな対象文字、文字列にもマッチするもののこと
○ワイルドカード
|ワイルドカード|内容                                                                                                |
|*             |任意の文字列を表し、パッケージを表す場合は任意のパッケージ1階層を、メソッドの引数では1つの引数を表す|
|..            |パッケージを表す場合は0個以上のパッケージを、メソッドの引数を表す場合は0個以上の任意の引数を表す    |
|+             |クラス名の後に記述し、クラスとそのサブクラスや実装クラスすべてを表す                                |
例）
1.DemoServiceクラスのメソッドにAdviceを適用する
execution(*com.example.service.DemoService.*(..))
2.DemoServiceクラスのselectで始まるメソッドにAdviceを適用する
execution(*com.example.service.DemoService.select*(..))
3.DemoServiceクラスの最初の引数がString型のメソッドにAdviceを適用する
execution(*com.example.service.DemoService.*(String, ..)
*/

