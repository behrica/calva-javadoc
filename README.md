# calva-javadoc

Small helper to make it easy to show JavaDoc for the "current form" in Calva (VS Code Clojure extension).

Features
- Show JavaDoc for the Java/interop member under the cursor or for the selected form.
- Uses [clojure.java.doc](https://github.com/clojure/java.doc) to look up javadoc metadata (can fetch javadocs for Maven artifacts).
- Works when the target class/method is on the REPL classpath and javadocs are published.

Quick install (example Calva custom snippet)
```clojure
{:name "jdoc"
 :key "j"
 :snippet
 (do
   (clojure.repl.deps/add-lib 'calva-javadoc/calva-javadoc
                              {:git/url "git@github.com:behrica/calva-javadoc.git"
                               :git/sha "2a745a1fe380d6aa38d9120bf3c7c2e7774fb0bb"})
   (require '[calva-javadoc])
   (calva-javadoc/view-javadoc-in-flare '$current-form))}
```

Usage
- Place the cursor on a Java interop form (e.g. |(.toString "hello")) or select a form.
- Trigger the snippet (or call the function) to open the JavaDoc in a Flare/web panel.
- clojure.java.doc may fetch javadoc jars from remote repositories — ensure network access.

Notes & troubleshooting
- The target class must be available on the REPL classpath.
- Not all libraries publish javadoc jars; lookups may fail for those.
- Network access and Maven-central (or other repositories) availability can affect results.

> [!WARNING] 
> The running the above snippet, will `eval` the second element of the form. 

Contributing
- PRs and issues welcome. Keep changes small and focused.

License
- See repository for license information.

