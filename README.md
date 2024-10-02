Brewmeister
-----------
>Brewing process simulation using ScalaFx, Ox and Scala 3.

Todo
----
1. Metric calcs
2. Persistence
3. App

Process
-------
>Steps used in ***all grain*** brewing. ***Extract*** brewing will exclude steps 3 - 7.
1. **Sanitizing** - Santize all brewing tools and containers.
2. **Preparing** - Prepare all ingredients for a brewing session.
3. **Malting** - Dry and optionally roast germinated grains (barley, wheat, oats or rye) for ***milling***.
4. **Milling** - Crush malted grains into a ***grist***, exposing its starches for ***mashing***.
5. **Mashing** - **Transfer** the ***grist*** to a ***mash tun*** with boiling water (148 - 158F). Then stir and let set for 60m, allowing for the conversion of starches into fermentable sugars - known as the ***wort***.
6. **Lautering** - Filter out solids from the ***wort***.
7. **Sparging** - Pour hot water on the ***wort*** and stir to extract more sugars from the grains.
8. **Boiling** - **Transfer** the ***wort*** to a ***brew kettle*** and bring to a boil. Then add ***hops*** and optional ***extract*** as required. Sustain boil for 60m.
9. **Cooling** - Cool the ***wort*** using a chiller, bringing the temperature down to 68 - 72F.
10. **Whirlpooling** - Stir the ***wort***, collecting hop solids, or ***trub***, at the bottom-center of the kettle. Optionally add ***hops***.
11. **Fermenting** - **Transfer** the ***wort*** to the primary ***fermenter***. Then **pitch** the ***yeast*** into the ***wort***, allowing for the conversion of sugars into alcohol and CO2. Let ferment for 2+ weeks at required temperature.
12. **Conditioning** - Optionally **transfer** ***wort*** to a secondary ***fermenter*** and add ***hops***. Let condition for 2+ days or longer at required temperature.
13. **Packaging** - Bottle / Can ( carbonate with priming sugar ) or Keg ( force carbonate ) the ***fermented and conditioned wort***. Let condition for 2 weeks at required temperature. Then refrigerate.
>Some beer recipes may require adjustments to this process.

Flow
----
1. app -- recipe --> brewer
2. brewer -- Sanitize --> Sanitizer --> Sanitizing | Sanitized --> Logger
3. brewer -- Prepare(recipe) --> Preparer --> Preparing | Prepared  --> Logger
4. brewer -- Malt(recipe) --> Malter --> Malting | Malted --> Logger
5. brewer -- Mill(recipe) --> Miller --> Milling | Milled --> Logger
6. brewer -- Mash(recipe) --> Masher --> Mashing | Mashed --> Logger
7. brewer -- Lauter --> Lauterer --> Lautering | Lautered --> Logger
8. brewer -- Sparge --> Sparger --> Spargering | Sparged --> Logger
9. brewer -- Boil(recipe) --> Boiler --> Boiling | Boiled --> Logger
10. brewer -- Cool --> Cooler --> Cooling | Cooled --> Logger
11. brewer -- Whirlpool(recipe) --> Whirlpooler --> Whirlpooling | Whirlpooled --> Logger
12. brewer -- Ferment(recipe) --> Fermenter --> Fermenting | Fermented --> Logger
13. brewer -- Condition --> Conditioner --> Conditioning | Conditioned --> Logger
14. brewer -- Package --> Packager

Model
-----
* Recipe 1 -> * Grain | Hop | Adjunct | Yeast
* Metrics 1 -> 1 Recipe

Actor
-----
* Brewer, Sanitizer, Preparer, Malter, Miller, Masher, Lauter, Sparger, Boiler, Cooler, Whirlpooler, Fermenter, Conditioner, Packager

Command
-------
* Sanitize, Prepare, Malt, Mill, Mash, Lauter, Sparge, Boil, Cool, Whirlpool, Ferment, Condition, Package

Event
-----
* Sanitizing, Sanitized, Preparing, Prepared, Malting, Malted, Milling, Milled, Mashing, Mashed, Lautering, Lautered, Sparging, Sparged, Boiling, Boiled, Cooling, Cooled, Wirlpooling, Whirlpooled, Fermenting, Fermented, Conditioning, Conditioned, Packaging, Packaged

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
1. java -jar .assembly/brewmeister-mac-0.1.jar
2. java -jar .assembly/brewmeister-m1-0.1.jar
3. java -jar .assembly/brewmeister-win-0.1.jar
4. java -jar .assembly/brewmeister-linux-0.1.jar

Deploy
------
>Consider these options:
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