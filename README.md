# clojure-template-benchmarks

I decided to do some decent Clojure templating benchmarks:

Template Engines tested:
str
hiccup
clabango (string literals)
clabango (from filesystem)

Test cases:
simple data injection
small list (50 items) generation
big list (1000 items) generation

<table>

    <tr>
      <th>Template Engine</th>
      <th>Simple Data Injection</th>
      <th>Small List</th>
      <th>Big List</th>
    </tr>
    <tr>
      <td>str</td>
    </tr>
    <tr>
      <td>hiccup</td>
    </tr>
    <tr>
      <td>clabango (string)</td>
    </tr>
    <tr>
      <td>clabango (files)</td>
    </tr>

</table>
str

hiccup

clabango string literals

clabango from filesystem

Copyright © 2013 bitemyapp
