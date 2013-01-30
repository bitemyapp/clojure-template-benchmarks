# clojure-template-benchmarks

I decided to do some decent Clojure templating benchmarks:

Test results are avg / standard deviation.

## Data
<table>

    <tr>
      <th>Template Engine</th>
      <th>Simple Data Injection</th>
      <th>Small List (50 items)</th>
      <th>Big List (1000 items)</th>
    </tr>
    <tr>
      <td>str</td>
      <td>165 ns / 16 ns</td>
      <td>14 us / 253 ns</td>
      <td>273 us / 14 us</td>
    </tr>
    <tr>
      <td>hiccup</td>
      <td>20 us / 367 ns</td>
      <td>1.1 ms / 59 us</td>
      <td>24 ms / 2.9 ms</td>
    </tr>
    <tr>
      <td>clabango (string)</td>
      <td>329 us / 5.7 us</td>
      <td>1.8 ms / 146 us</td>
      <td>20.6 us / 799 us</td>
    </tr>
    <tr>
      <td>clabango (files)</td>
      <td>478 us / 42 us</td>
      <td>1.79 ms / 52 us</td>
      <td>19.9 ms / 573 us</td>
    </tr>

</table>

## Conclusions

str is really fast and a huge waste of programmer time.

Copyright © 2013 bitemyapp
