(defproject test-aws-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [com.cognitect.aws/api "0.8.635"]
                 [com.cognitect.aws/endpoints "1.1.12.373"]
                 [com.cognitect.aws/dynamodb "825.2.1262.0"]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
