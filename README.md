# clojure-template-benchmarks

I decided to do some decent Clojure templating benchmarks:

Test results are avg / standard deviation.

## Data

Units are in microseconds (us)

<table>

    <tr>
      <th>Template Engine</th>
      <th>Simple Data Injection</th>
      <th>Small List (50 items)</th>
      <th>Big List (1000 items)</th>
    </tr>

    <tr>
      <td>str</td>
      <td>0.165 us / 0.016 us</td>
      <td>14 us / 0.253 us</td>
      <td>273 us / 14 us</td>
    </tr>

    <tr>
      <td>hiccup</td>
      <td>20 us / 0.367 us</td>
      <td>1100 us / 59 us</td>
      <td>24,000 us / 2900 us</td>
    </tr>

    <tr>
      <td>hiccup (type-hinted)</td>
      <td>0.335 us / 0.013 us</td>
      <td>25 us / 0.431 us</td>
      <td>498 us / 6.2 us</td>
    </tr>

    <tr>
      <td>clabango (string)</td>
      <td>329 us / 5.7 us</td>
      <td>1800 us / 146 us</td>
      <td>20,600 us / 799 us</td>
    </tr>

    <tr>
      <td>clabango (files)</td>
      <td>478 us / 42 us</td>
      <td>1,790 us / 52 us</td>
      <td>19,900 us / 573 us</td>
    </tr>

    <tr>
      <td>stencil (string)</td>
      <td>58 us / 6 us</td>
      <td>212 us / 27 us</td>
      <td>930 us / 37 us</td>
    </tr>

    <tr>
      <td>stencil (file)</td>
      <td>1.2 us / 0.022 us</td>
      <td>38 us / 0.943 us</td>
      <td>784 us / 16 us</td>
    </tr>

    <tr>
      <td>tinsel</td>
      <td>23.7 us / 5.5 us</td>
      <td>1,100 us / 52 us</td>
      <td>22,000 us / 770 us</td>
    </tr>

    <tr>
      <td>laser</td>
      <td>73.6 us / 1.5 us</td>
      <td>243 us / 18 us</td>
      <td>3,400 us / 61 us</td>
    </tr>

    <tr>
      <td>enlive</td>
      <td>38 us / 0.435 us</td>
      <td>320 us / 10 us</td>
      <td>6,400 us / 450 us</td>
    </tr>

</table>

## Conclusions

+ str is really fast and a huge waste of programmer time.
+ clabango from filesystem templates or string literals are equivalent
+ clabango and hiccup are equivalent in performance
+ stencil from string literals is faster than clabango and hiccup,
+ stencil from files is even faster by a marginal amount.
+ Enlive and Laser are faster than Hiccup and Clabango

Copyright © 2013 bitemyapp
