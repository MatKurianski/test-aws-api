(ns test-aws-api.core
  (:require [cognitect.aws.client.api :as aws]))

;; Totally inspired by https://onexlab-io.medium.com/localstack-dynamodb-8befdaac802b

(def ddb (aws/client {:api :dynamodb
                      :endpoint-override {:protocol :http
                                          :hostname "localhost"
                                          :port 4566}}))

(def table-name "Music")

(comment (aws/doc ddb :CreateTable)
         (aws/invoke ddb {:op :CreateTable
                          :request
                          {:TableName table-name
                           :AttributeDefinitions [{:AttributeName "Artist" :AttributeType "S"}
                                                  {:AttributeName "SongTitle" :AttributeType "S"}]
                           :KeySchema [{:AttributeName "Artist" :KeyType "HASH"}
                                       {:AttributeName "SongTitle" :KeyType "RANGE"}]
                           :ProvisionedThroughput {:ReadCapacityUnits 10.0 :WriteCapacityUnits 10.0}}})

         (aws/doc ddb :DescribeTable)
         (aws/invoke ddb {:op :DescribeTable
                          :request {:TableName table-name}})

         (aws/doc ddb :PutItem)
         (aws/invoke ddb {:op :PutItem
                          :request {:TableName table-name
                                    :Item {"Artist" {:S "No One You Know"}
                                           "SongTitle" {:S "Call Me Today"}
                                           "AlbumTitle" {:S "AlbumTitle"}
                                           "Awards" {:N "1"}}}})

         (aws/doc ddb :Scan)
         (aws/invoke ddb {:op :Scan
                          :request {:TableName table-name}}))
