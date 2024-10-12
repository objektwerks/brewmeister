Brewmeister
-----------
>Brewing process simulation and tracker using ScalaFx, Ox, uPickle and Scala 3.

Todo
----
* Metrics
* Persistence
* App

Process
-------
>Steps used in ***all grain*** brewing. ***Extract*** brewing will exclude steps 3 - 7.
1. **Sanitizing** - Santize all brewing tools and containers.
    1. input: dirty brewing implements
    2. output: clean brewing implements
2. **Preparing** - Prepare all ingredients for a brewing session.
    1. input - recipe
    2. output - recipe ingredients
3. **Malting** - Dry and optionally roast germinated grains (barley, wheat, oats or rye) for ***milling***.
    1. input - grains
    2. output - dry and/or roasted grains
4. **Milling** - Crush malted grains into a ***grist***, exposing its starches for ***mashing***.
    1. input - grains
    2. output - crushed grains as ***grist***
5. **Mashing** - **Transfer** the ***grist*** to a ***mash tun*** with boiling water at required **temp**. Stir and let set for required **duration**, allowing for the conversion of starches into fermentable sugars - known as the ***wort***.
    1. input - grist, optional **adjuncts**
    2. constraints - required mashing **temp** range and **duration**
    3. output - wort, **pH**
6. **Lautering** - Filter out solids from the ***wort***.
    1. input - wort
    2. output - wort
7. **Sparging** - Pour hot water on the ***wort*** and stir to extract more sugars from the grains.
    1. input - wort
    2. output - wort, **mash efficiency**
8. **Boiling** - **Transfer** the ***wort*** to a ***brew kettle*** and bring to a boil at required **temp**. Sustain boil for required **duration**.
    1. input - wort, hops, optional **adjuncts**
    2. constraints - required boiling **temp** range and **duration**
    3. output - wort
9. **Cooling** - Cool the ***wort*** using a chiller, until required **temp** is reached.
    1. input - wort
    2. constraints - required cooling **temp** range
    3. output - wort
10. **Whirlpooling** - Stir the ***wort***, collecting hop solids, or ***trub***, at the bottom-center of the kettle.
    1. input - wort, optional **hops**
    2. output - **original gravity**
11. **Fermenting** - **Transfer** the ***wort*** to the primary ***fermenter***. Then **pitch** the ***yeast*** into the ***wort***, allowing for the conversion of sugars into alcohol and CO2. Ferment for required **temp** and **duration**.
    1. input - wort, **yeast**
    2. constraints - required fermenting **temp** range and **duration**
    3. output - wort, **final gravity**
12. **Conditioning** - **Transfer** ***wort*** to a secondary ***fermenter***. Condition for required **temp** and **duration**.
    1. input - wort, optional **adjuncts** and **hops**
    2. constraints - required conditioning **temp** range and **duration**
    3. output - wort, **srm color**
13. **Packaging** - Bottle / Can ( carbonate with priming sugar ) or Keg ( force carbonate ) the ***fermented and conditioned wort***. Condition for required **temp** and **duration**. Then refrigerate at required **temp**.
    1. input - wort, priming sugar or forced carbonation
    2. constraints - required conditioning **temp** range and **duration**; required refrigeration **temp** range
    3. output - beer, **ibu**, **abv**, **abw**, **calories**, **brewhouse efficiency**
>Some beer recipes ***may*** require adjustments to this process.

Flows
-----
1. App -- create --> Batch | Listener
3. App -- batch, listener --> Brewer -- batch, listener --
    1. Sanitize --> **Sanitizer** -- Sanitized --> Listener
    2. Prepare --> **Preparer** -- Prepared --> Listener
    3. Malt --> **Malter** -- Malted --> Listener
    4. Mill --> **Miller** -- Milled --> Listener
    5. Mash --> **Masher** -- Mashed --> Listener
    6. Lauter --> **Lauterer** -- Lautered --> Listener
    7. Sparge --> **Sparger** -- Sparged --> Listener
    8. Boil --> **Boiler** -- Boiled --> Listener
    9. Cool --> **Cooler** -- Cooled --> Listener
    10. Whirlpool --> **Whirlpooler** -- Whirlpooled --> Listener
    11. Ferment --> **Fermenter** -- Fermented --> Listener
    12. Condition --> **Conditioner** -- Conditioned --> Listener
    13. Package --> **Packager** -- Packaged --> Listener

Model
-----
>See [Entity](https://github.com/objektwerks/brewmeister/blob/main/src/main/scala/objektwerks/Entity.scala) for details.
* Recipe 1 --> * Grain | Hop | Adjunct | Yeast
* Batch 1 --> 1 Recipe | Metrics

Actor
-----
* Sanitizer, Preparer, Malter, Miller, Masher, Lauter, Sparger, Boiler, Cooler, Whirlpooler, Fermenter, Conditioner, Packager

Command
-------
* Sanitize, Prepare, Malt, Mill, Mash, Lauter, Sparge, Boil, Cool, Whirlpool, Ferment, Condition, Package

Event
-----
* Sanitized, Prepared, Malted, Milled, Mashed, Lautered, Sparged, Boiled, Cooled, Whirlpooled, Fermented, Conditioned, Packaged

Metric
------
1. **Temperature** - Measured during mashing, boiling, cooling, fermentation, conditioning, packaging and refrigeration.
    1. Method: ***thermometer***.
2. **pH** - The pH balance of the mash.
    1. Method: ***pH meter***.
3. **Plato: P ~ Specific Gravity: SG** See [Table](https://www.brewersfriend.com/plato-to-sg-conversion-chart/)
    1. P = ( -1 * 616.868 ) + ( 1111.14 * SG ) - ( 630.272 * SG ^ 2 ) + ( 135.997 * SG ^ 3 )
    2. SG = 1 + ( P / ( 258.6 - ( ( P / 258.2 ) * 227.1 ) ) )
4. **Original Gravity: OG** - Original Gravity measures the gravity of the wort **before** fermentation.
    1. Method: ***hydrometer***.
    2. Constraint: Sample must be at 20C / 68F.
    3. Range: 1.000 - 1.130
5. **Final Gravity: FG** - Final Gravity measures the gravity of the wort **after** fermentation.
    1. Method: ***hydrometer***.
    2. Constraint: Sample must be at 20C / 68F.
    3. Range: 1.000 - 1.130
6. **Color: SRM** - Standard Reference Method (SRM) represents the color of a beer. Malt Color Units (MCU). Obtain Lovibond value from grain package.
    1. Method: ***recipe***
    2. MCU = ( Grain Weight (lbs) ) * ( Grain Color (degrees lovibond) ) / ( Volume(gallons) )
    3. SRM = 1.4922 * ( MCU ^ 0.6859 )
    4. Range: 1 - 40
7. **Hop Bitterness: IBU** - International Bittering Units ( IBU ) is the measure of beer bitterness that comes from boiling hops.
    1. Method: ***recipe***
    2. Hop Weight: Actual mass of hops, excluding moisture.
    3. Hop Volume: Actual mass of hops, including moisture. 1 oz of **dry** hops equals 4-6 oz of **wet** hops.
    4. Hop Utilization % = ( Hop Alpha Acid (%) * Hop Weight (dry oz) ) / Hop Volume (fluid oz)
    5. IBU = ( Hop Weight (oz) * Hop Alpha Acid (%) * Hop Utilization (%) ) / 7.25
    6. Range: 0 - 120
8. **Alcohol: ABV** - Measured by volume, the amount of alcohol produced in a wort during fermentation.
    1. Method: ***metrics***
    2. ABV % = ( OG - FG ) * 131
    3. Range: 3.0 - 13.0 %
9. **Alcohol: ABW** - Measured by weight, the amount of alcohol produced in a wort during fermentation.
    1. Method: ***metrics***
    2. ABW % = ( 0.79 * ABV (%) ) / FG
    3. Range: 3.0 - 10.0 %
10. **Calories** - The number of calories in a beer.
    1. Method: ***metrics***
    2. Volume = Beer container (oz)
    3. Alcohol Calories = ( OG - FG ) * 7.5
    4. Carbohydrate Calories = ( FG * 13 ) * Volume
    5. Calories = Alcohol Calories + Carbohydrate Calories
    6. Range: 10 - 600
11. **Mash Efficiency: ME** - Mash Efficiency calculates the percentage of fermentable extract extracted during the mash and sparging steps.
    1. Method: ***brewer input and recipe***
    2. Actual Mash Extract = The amount of fermentable extract collected from the mash tun.
    3. Potential Mash Extract = The maximum extract potential of the grain bill, calculated on the malt’s extract potential and the grain bill’s weight and volume.
    4. ME % = ( Actual Extract / Potential Extract ) * 100
    5. Range: 70.0 - 100.0 %
    6. Ideal: 80.0 - 90.0 %
12. **Brewhouse Efficiency: BE** - Brewhouse Efficiency accounts for losses throughout the entire brewing process.
    1. Method: ***brewer input and metrics***
    2. Actual Fermentable Extract = The amount of fermentable extract collected from the brewhouse.
    3. Potential Fermentable Extract = The maximum extract potential of the grain bill, assuming 100% efficiency throughout the brewing process.
    4. BE % = ( Actual Fermentable Extract / Ideal Fermentable Extract ) * 100
    5. Range: 60.0 - 100.0 %
    6. Ideal: 72.0 - 80.0 %

Metrics Flow
------------
1. mashing -- brewer pH meter --> pH
2. sparging -- brewer input ( actual mash extract ) and recipe ( potential mash extract ) --> mashEfficiency
3. whirlpooler -- brewer hydrometer --> originalGravity
4. fermenter -- brewer hydrometer --> finalGravity
5. conditioner -- recipe ( grain weight, grain color, batch volume ) --> srmColor
6. packager -- brewer input, recipe, metrics -->
    1. ibuBitterness ( recipe -> hops -> hop -> alphaAcid, weight, volume )
    2. alcoholByVolume ( metrics -> OG, metrics -> FG )
    3. alcoholByWeight ( metrics -> abv, metrics -> FG )
    4. calories ( metrics -> volume, metrics -> OG, metrics -> FG )
    5. brewhouseEfficiency ( brewer input -> actual fermentable extract, recipe -> grains -> grain -> potential fermentable extract)

Assembly
--------
>To build for a "mac", "m1', "win" or "linux" os target:
1. sbt -Dtarget="mac" clean test assembly copyAssemblyJar
2. sbt -Dtarget="m1" clean test assembly copyAssemblyJar
3. sbt -Dtarget="win" clean test assembly copyAssemblyJar
4. sbt -Dtarget="linux" clean test assembly copyAssemblyJar

Execute
-------
>To execute an assembled jar locally:
1. java -jar .assembly/brewmeister-mac-0.25.jar
2. java -jar .assembly/brewmeister-m1-0.25.jar
3. java -jar .assembly/brewmeister-win-0.25.jar
4. java -jar .assembly/brewmeister-linux-0.25.jar

Deploy
------
>Options:
1. [jDeploy](https://www.npmjs.com/package/jdeploy)
2. [Conveyor](https://hydraulic.software/index.html)

Resources
---------
* [Doc's Brewing Glossary](https://docmckee.com/beer/)
* [Brewing Fundamentals](https://beerconnoisseur.com/articles/beer-101-fundamental-steps-brewing)
* [Brulosophy](https://brulosophy.com/)
* [Beer XML](https://www.beerxml.com/)
* [Brewing Measurement Devices](https://www.youtube.com/watch?v=WZgmTy_nDqs)
* [OG & FG Chart](https://www.brewersfriend.com/2017/05/07/beer-styles-original-gravity-and-final-gravity-chart-2017-update/)
* [More Ways to Get Hoppy](https://beerandbrewing.com/learning-lab-more-ways-to-get-hoppy/)
* [Beer Color](https://beermaverick.com/understanding-srm-and-lovibond-beer-color-calculations/)

License
-------
>Copyright (c) [2024] Objektwerks

>Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    * http://www.apache.org/licenses/LICENSE-2.0

>Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.