(set-env!
 :dependencies  '[[org.clojure/clojure                 "1.7.0"]
                  [boot/core                           "2.6.0"]
                  [adzerk/bootlaces                    "0.1.13"]
                  [cheshire                            "5.6.3"]
                  [degree9/boot-semver                 "1.3.6"]
                  [degree9/boot-exec                   "0.4.0"]
                  [degree9/boot-npm                    "0.2.0"]]
 :resource-paths   #{"src"})

(require
 '[adzerk.bootlaces :refer :all]
 '[degree9.boot-semver :refer :all])

(task-options!
  pom {:project 'degree9/boot-bower
       :version (get-version)
       :description "boot-clj task for wrapping bower"
       :url         "https://github.com/degree9/boot-bower"
       :scm         {:url "https://github.com/degree9/boot-bower"}})

(deftask develop
  "Build boot-bower for development."
  []
  (comp
   (watch)
   (version :no-update true
            :minor 'inc
            :patch 'zero
            :pre-release 'snapshot)
   (target  :dir #{"target"})
   (build-jar)))

(deftask deploy
  "Build boot-bower and deploy to clojars."
  []
  (comp
   (version :minor 'inc
            :patch 'zero)
   (target  :dir #{"target"})
   (build-jar)
   (push-release)))
