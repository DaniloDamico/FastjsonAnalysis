# FastjsonAnalysis

Studio della coverage su FastJSON (i.e. Progetto "1+").

* Alibaba FastJSON ver 1.2.79
* branch su GitHub: https://github.com/alibaba/fastjson/tree/1.2.79
* binari su Maven Central: https://search.maven.org/artifact/com.alibaba/fastjson/1.2.79/jar
* release completa su Maven Central: https://repo1.maven.org/maven2/com/alibaba/fastjson/1.2.79/

Classi da testare:

* fastjson/src/test/java/com/alibaba/json/bvt/serializer/MapTest.java
* fastjson/src/test/java/com/alibaba/json/bvt/serializer/JSONFieldTest.java

## Metodo

Per ogni classe di test proporre una nuova implementazione:
* basata su Junit 4
* che preveda esclusivamente la dichiarazione di test parametrici
* che usi uno o più metodi “configure” per legare i parametri passati con il runner Parametrized o il costruttore
  * l’istanza sotto test DEVE ESSERE considerata un parametro della procedura di test impostato dai
  metodi “configure”. In altre parole il “@Test” NON DEVE ESSERE responsabile dell’allocazione
  dell’istanza testata.

Per ognuna delle classi analizzare l’andamento della copertura strutturale in funzione del cambio dei valori nei parametri dei test motivandone il perché
* Come cambia la copertura strutturale in base alla selezione di un qualsiasi sottoinsieme dei parametri considerati?

È lecito assumere un approccio al testing totalmente white-box