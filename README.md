# clojure-template-benchmarks

I decided to do some decent Clojure templating benchmarks:

Test results are avg / standard deviation.

## Data

The unit 'us' is microseconds. Typical bullshit "ran this on my macbook" arrangement. Run it on your own thing if you care.

<table>

    <tr>
      <th>Template Engine</th>
      <th>Simple Data Injection</th>
      <th>Small List (50 items)</th>
      <th>Big List (1000 items)</th>
    </tr>

    <tr>
      <td>str</td>
      <td>0.365 us / 0.002 us</td>
      <td>29 us / 0.220 us</td>
      <td>572 us / 2 us</td>
    </tr>

    <tr>
      <td>hiccup</td>
      <td>0.673 us / 0.006 us</td>
      <td>35 us / 1 us</td>
      <td>682 us / 3 us</td>
    </tr>

    <tr>
      <td>hiccup (type-hinted)</td>
      <td>Identical to or slower than non-type-hinted hiccup.</td>
      <!-- <td>0.335 us / 0.013 us</td>
      <td>25 us / 0.431 us</td>
      <td>498 us / 6.2 us</td> -->
    </tr>

    <tr>
      <td>clabango (string)</td>
      <td>544 us / 6.7 us</td>
      <td>2452 us / 17 us</td>
      <td>30,000 us / 193 us</td>
    </tr>

    <tr>
      <td>clabango (files)</td>
      <td>665 us / 8.7 us</td>
      <td>2,617 us / 22 us</td>
      <td>30,450 us / 390 us</td>
    </tr>

    <tr>
      <td>stencil (string)</td>
      <td>90 us / 0.5 us</td>
      <td>290 us / 2.7 us</td>
      <td>1,300 us / 13 us</td>
    </tr>

    <tr>
      <td>stencil (file)</td>
      <td>2.2 us / 0.019 us</td>
      <td>58 us / 0.465 us</td>
      <td>1,148 us / 10 us</td>
    </tr>

    <tr>
      <td>mustache.clj (file)</td>
      <td>0.947 us / 0.006 us</td>
      <td>26 us / 0.206 us</td>
      <td>541 us / 4.7 us</td>
    </tr>

    <tr>
      <td>tinsel</td>
      <td>0.678 us / 0.006 us</td>
      <td>34 us / 0.175 us</td>
      <td>679 us / 7.1 us</td>
    </tr>

    <tr>
      <td>laser</td>
      <td>404 us / 9.4 us</td>
      <td>3,770 us / 26 us</td>
      <td>68,000 us / 925 us</td>
    </tr>

    <tr>
      <td>laser (type-hinted)</td>
      <td>401 us / 3.1 us</td>
      <td>3,764 us / 18 us</td>
      <td>69,408 us / 1,028 us</td>
    </tr>

    <tr>
      <td>enlive</td>
      <td>47 us / 0.397 us</td>
      <td>385 us / 8.5 us</td>
      <td>6,800 us / 51 us</td>
    </tr>

</table>

## Conclusions

+ (str ...), Mustache.clj, Tinsel, and Hiccup are all fast.
+ Raynes wasn't paying attention when he refactored laser. :P
+ Enlive hasn't changed at all.
+ Stencil seems less impressive than it used to, but it's still faster than most options.
+ Clabango has gotten worse.

Copyright © 2013 bitemyapp
