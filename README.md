Brewmeister
-----------
>Brewing process simulation and tracker using ScalaFx, Ox, uPickle and Scala 3.

Todo
----
* Actors
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
    2. output - recipe ingredients prepared
3. **Malting** - Dry and optionally roast germinated grains (barley, wheat, oats or rye) for ***milling***.
    1. input - grains
    2. output - dry and/or roasted grains
4. **Milling** - Crush malted grains into a ***grist***, exposing its starches for ***mashing***.
    1. input - grains
    2. output - crushed grains as ***grist***
5. **Mashing** - **Transfer** the ***grist*** to a ***mash tun*** with boiling water (148 - 158F). Then stir and let set for 60m, allowing for the conversion of starches into fermentable sugars - known as the ***wort***. **Optionally** add ***adjuncts***.
    1. input - grist ( crushed grains )
    2. output - wort and ***pH***
6. **Lautering** - Filter out solids from the ***wort***.
    1. input - wort
    2. output - wort
7. **Sparging** - Pour hot water on the ***wort*** and stir to extract more sugars from the grains.
    1. input - wort
    2. output - wort and ***mash efficiency***
8. **Boiling** - **Transfer** the ***wort*** to a ***brew kettle*** and bring to a boil. Then add ***hops*** as required. **Optionally** add ***adjuncts***. Sustain boil for 60m.
    1. input - wort
    2. output - enhanced wort
9. **Cooling** - Cool the ***wort*** using a chiller, bringing the temperature down to 68 - 72F.
    1. input - wort
    2. output - wort
10. **Whirlpooling** - Stir the ***wort***, collecting hop solids, or ***trub***, at the bottom-center of the kettle. **Optionally** add ***hops***.
    1. input - wort
    2. output - optionally enhanced wort ***with*** Original Gravity (OG) reading
11. **Fermenting** - **Transfer** the ***wort*** to the primary ***fermenter***. Then **pitch** the ***yeast*** into the ***wort***, allowing for the conversion of sugars into alcohol and CO2. Let ferment for 2+ weeks at required temperature.
    1. input - wort
    2. output - yeast-pitched wort ***with*** Final Gravity (FG) reading
12. **Conditioning** - **Optionally** **transfer** ***wort*** to a secondary ***fermenter***. Let condition for 2+ days or longer at required temperature. **Optionally** add ***adjuncts*** and/or ***hops***.
    1. input - wort
    2. output - optionally enhanced wort ***with*** color (SRM) reading
13. **Packaging** - Bottle / Can ( carbonate with priming sugar ) or Keg ( force carbonate ) the ***fermented and optionally conditioned wort***. Let condition 
for 2+ days at required temperature. Then refrigerate.
    1. input - wort
    2. output - beer ***with*** IBU ***with*** ABV ***with*** ABW ***with*** Brewhouse Efficiency ratings
>Some beer recipes ***may*** require adjustments to this process.

Flows
-----
>Allows ***App*** to invoke the entire brewing process in a single call to a ***Brewer*** instance.
1. **App** -- Brew --> **Brewer** -- Brewed --> **App**

>Allows ***App*** to call brewing process steps as required via a ***Brewer*** instance.
1. ***App*** -- create --> ***Brewer*** --
    1. Sanitize --> **Sanitizer** -- Sanitized --> App
    2. Prepare --> **Preparer** -- Prepared --> App
    3. Malt --> **Malter** -- Malted --> App
    4. Mill --> **Miller** -- Milled --> App
    5. Mash --> **Masher** -- Mashed --> App
    6. Lauter --> **Lauterer** -- Lautered --> App
    7. Sparge --> **Sparger** -- Sparged --> App
    8. Boil --> **Boiler** -- Boiled --> App
    9. Cool --> **Cooler** -- Cooled --> App
    10. Whirlpool --> **Whirlpooler** -- Whirlpooled --> App
    11. Ferment --> **Fermenter** -- Fermented --> App
    12. Condition --> **Conditioner** -- Conditioned --> App
    13. Package --> **Packager** -- Packaged --> App

Model
-----
* Recipe 1 --> * Grain | Hop | Adjunct | Yeast
* Process 1 --> * Step 1 --> 1 Container
* Process 1 --> 1 Recipe | Metrics

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
1. **Temperature** - Measured at mashing, boiling, cooling and daily throughout fermentation. Use a thermometer.
2. **pH** - The pH balance of the mash. Use a pH meter.
3. **Plato: P ~ Specific Gravity: SG** See [Table](https://www.brewersfriend.com/plato-to-sg-conversion-chart/)
    1. P = ( -1 * 616.868 ) + ( 1111.14 * SG ) - ( 630.272 * SG ^ 2 ) + ( 135.997 * SG ^ 3 )
    2. SG = 1 + ( P / ( 258.6 - ( ( P / 258.2 ) * 227.1 ) ) )
4. **Original Gravity: OG** - Original Gravity measures the gravity of wort before fermentation.
    1. Use a **Hydrometer** to measure OG before fermentation. Sample must be at 20C / 68F.
    2. Range: 1.000 - 1.130
5. **Final Gravity: FG** - Final Gravity measures the gravity of wort after fermentation.
    1. Use a **Hydrometer** to measure FG after fermentation. Sample must be at 20C / 68F.
    2. Range: 1.000 - 1.130
6. **Color: SRM** - Standard Reference Method (SRM) represents the color of a beer. Malt Color Units (MCU).
    1. MCU = ( Grain Weight (lbs) ) * ( Grain Color (degrees lovibond) ) / ( Volume(gallons) )
    2. SRM = 1.4922 * ( MCU * 0.6859 )
    3. Range: 1 - 40
7. **Hop Bitterness: IBU** - International Bittering Units ( IBU ) is the measure of bitterness of beer that comes from the boiling of hops.
    1. Hop Utilization(%) = ( Hop Alpha Acid (%) * Hop Weight (oz) ) / Hop Volume (gallons)
    2. IBU = ( Hop Weight (oz) * Hop Alpha Acid (%) * Hop Utilization (%) ) / 7.25
    3. Range: 0 - 120
8. **Alcohol: ABV** - Measured by volume, the amount of alcohol produced in a wort during fermentation.
    1. ABV % = ( OG - FG ) * 131
    2. Range: 3.0 - 13.0 %
9. **Alcohol: ABW** - Measured by weight, the amount of alcohol produced in a wort during fermentation.
    1. ABW % = ( 0.79 * ABV (%) ) / FG
    2. Range: 3.0 - 10.0 %
10. **Calories** - The number of calories in a beer.
    1. Volume = Beer bottle in ounces(oz).
    2. Alcohol Calories = ( OG - FG ) * 7.5
    3. Carbohydrate Calories = ( FG * 13 ) * Volume
    4. Calories = Alcohol Calories + Carbohydrate Calories
    5. Range: 10 - 600
11. **Mash Efficiency: ME** - Mash Efficiency calculates the percentage of fermentable extract extracted during the mash and sparging steps.
    1. Actual Extract = The amount of fermentable extract collected from the mash tun.
    2. Potential Extract = The maximum extract potential of the grain bill, calculated based on the malt’s extract potential and the grain bill’s weight and volume.
    3. ME % = ( Actual Extract / Potential Extract ) * 100
    4. Range: 70.0 - 100.0 %
    5. Ideal: 80.0 - 90.0 %
12. **Brewhouse Efficiency: BE** - Brewhouse Efficiency accounts for losses throughout the entire brewing process.
    1. Actual Fermentable Extract = The amount of fermentable extract collected from the brewhouse.
    2. Ideal Fermentable Extract = The maximum extract potential of the grain bill, assuming 100% efficiency throughout the brewing process.
    3. BE % = ( Actual Fermentable Extract / Ideal Fermentable Extract ) * 100
    4. Range: 60.0 - 100.0 %
    5. Ideal: 72.0 - 80.0 %

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
1. java -jar .assembly/brewmeister-mac-0.18.jar
2. java -jar .assembly/brewmeister-m1-0.18.jar
3. java -jar .assembly/brewmeister-win-0.18.jar
4. java -jar .assembly/brewmeister-linux-0.18.jar

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