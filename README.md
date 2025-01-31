Brewmeister
-----------
>A beer brewing simulator, using ScalaFx, ControlsFx, uPickle, Os-Lib, Ox, jDeploy and Scala 3, that allows
>a brewer to **build** recipes and **brew** batches using the classic beer brewing process of sanitizing,
>preparing, malting, milling, mashing, lautering, sparging, boiling, cooling, whirlpooling, fermenting,
>conditioning and kegging.

>Allows a brewer to:
1. **define** a beer recipe;
2. **provide** batch input properties; and
3. **simulate** the brewing of a batch via the 13-step beer brewing process.

>In other words, Brewmeister can simulate the entire beer brewing process - without all the hard work,
>allowing a brewer to run ***what-if*** scenarios using different beer recipes and batch input properties.

Install
-------
1. Click [Brewmeister](https://www.jdeploy.com/~brewmeister) to download app installer.
2. Select a target platform to download a file like: Brewmeister Installer-$version_25VJ-arm64.tar.gz | .zip
3. Decompress the tar.gz or zip file - by double clicking it or using **unzip** or **tar** via the commandline.
4. Double-click the Brewmeister Installer-$version_25VJ.app to install app.
5. The Brewmeister app should now be installed. Select app icon to launch.
>This install has been tested on macOS.

App
---
>**Recipes:**
* Select **add** to build a new recipe.
* Select **brew** to simulate the brewing of a **selected** recipe.
* Select **save** to save the **selected** and **edited** recipe.
* Select **remove** to delete a **selected** recipe.

>**Batches:**
* Select a **listed** batch to analyze batch results.
* Select **log** to view the batch log.
* Select **process** to view the batch process.
* Select **remove** to delete a **selected** batch.

>**Default Recipe:**
* **Brewmeister IPA** is the default recipe. It can be removed; but it will be added at **App** startup - if ***NO*** recipes exist.

Assembly
--------
1. sbt clean test assembly copyAssemblyJar

Execute
-------
1. java -jar .assembly/brewmeister-$version.jar ( or double-click executable jar )

Deploy
------
1. edit build.sbt ( jarVersion + version )
2. edit package.json ( version + jdeploy / jar )
3. sbt clean test assembly copyAssemblyJar
4. perform github release ( from https://github.com/objektwerks/brewmeister )
5. npm login
6. jdeploy publish ( to https://www.jdeploy.com/~brewmeister )
7. check email for npm message
>See [jDeploy Docs](https://www.jdeploy.com/docs/manual/#_getting_started) for details.

jDeploy Install
---------------
1. Setup npm account at npmjs.com
2. Install node, which installs npm, which bundles npx.
3. Install jdeploy via npm - *npm install -g jdeploy*
4. Add icon.png ( 256 x 256, 512 x 512 ) to project root and resources/image.
5. Edit jDeploy *package.json* as required.
6. Add *jdeploy* and *jdeploy-bundle* to .gitignore
>See [jDeploy Docs](https://www.jdeploy.com/docs/manual/#_getting_started) for details.
   
jDeploy Issues
--------------
>The following issues with were encountered:
1. ***jDeploy publish*** fails due to npm *2fa* one-time password error. Solution: https://github.com/shannah/jdeploy/issues/74
2. ***macOS app icon*** not displayed in Dock and Launchpad.
    1. Ensure app icon ( ./icon.png + ./src/main/resources/image/icon.png ) is at least 256 x 256. 512 x 512 is recommended.
    2. See objektwerks.ui.App stage.icons, Taskbar and Toolkit code.
3. Much of the old sbt assembly plugin code is no longer required. See build.sbt for details.
4. The build.sbt tasks, *createAssemblyDir* and *copyAssemblyJar*, are not absolutely required, with assembly output copied to ./target/... by default.

Process
-------
>Steps used in ***all grain*** brewing. ***Extract*** brewing excludes steps 3-7.
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
    2. constraints - required conditioning **temp** range and **duration**
    3. output - beer, **ibu**, **abv**, **abw**, **calories**, **brewhouse efficiency**, kegging **temp**
>Some beer recipes ***may*** require adjustments to this process.

Flow
----
>**Package / Packager / Packaged** has been replaced with **Keg / Kegger / Kegged** for *simplicity* in the design.
1. App -- create --> Recipe | Listener | Brewer
2. App -- command --> Brewer -- handle( command )
    1. Sanitize --> **Sanitizer** -- Sanitizing | Sanitized --> Listener
    2. Prepare --> **Preparer** -- Preparing | Prepared --> Listener
    3. Malt --> **Malter** -- Malting | Malted --> Listener
    4. Mill --> **Miller** -- Milling | Milled --> Listener
    5. Mash --> **Masher** -- Mashing | Mashed --> Listener
    6. LogMashingTempPh --> **Masher** -- MashingTempPhLogged --> Listener
    7. Lauter --> **Lauterer** -- Lautering | Lautered --> Listener
    8. Sparge --> **Sparger** -- Sparing | Sparged --> Listener
    9. LogMashEfficiency --> **Sparger** -- MashEfficiencyLogged --> Listener
    10. Boil --> **Boiler** -- Boiling | Boiled --> Listener
    11. Cool --> **Cooler** -- Cooling | Cooled --> Listener
    12. Whirlpool --> **Whirlpooler** -- Whirlpooling | Whirlpooled --> Listener
    13. LogBoilingCoolingTempOriginalGravity --> **Whirlpooler** -- BoilingCoolingTempOriginalGravityLogged --> Listener
    14. Ferment --> **Fermenter** -- Fermenting | Fermented --> Listener
    15. LogFermentingTempFinalGravity --> **Fermenter** -- FermentingTempFinalGravityLogged --> Listener
    16. Condition --> **Conditioner** -- Conditioning | Conditioned --> Listener
    17. LogConditioningTempSrmColor --> **Conditioner** -- ConditioningTempSrmColorLogged --> Listener
    18. Keg --> **Kegger** -- Kegging | Kegged --> Listener
    19. LogKeggingTempBrewhouseEfficiency --> **Kegger** -- KeggingTempBrewhouseEfficiencyLogged --> Listener
3. Listener -- events -> Batch

Model
-----
* Recipe 1 --> * Grain | Hop | Adjunct | Yeast
* Brewer 1 -- brews --> * Command
* Brewer 1 -- delegates --> 1 Listener
* Listener 1 -- collects --> * Event
* Listener 1 -- builds --> * Batch
* Batch 1 --> 1 Log | Process

Command
-------
* Sanitize, Prepare, Malt, Mill, Mash, LogMashingTempPh, Lauter, Sparge, LogMashEfficiency, Boil, Cool, Whirlpool, LogBoilingCoolingTempOriginalGravity, Ferment, LogFermentingTempFinalGravity, Condition, LogConditioningTempSrmColor, Keg, LogBrewhouseEfficiency

Assistant
---------
* Sanitizer, Preparer, Malter, Miller, Masher, Lauter, Sparger, Boiler, Cooler, Whirlpooler, Fermenter, Conditioner, Kegger

Event
-----
* Sanitized, Prepared, Malted, Milled, Mashed, MashingTempPhLogged, Lautered, Sparged, MashEfficiencyLogged, Boiled, Cooled, Whirlpooled, BoilingCoolingTempOriginalGravityLogged, Fermented, FermentingTempFinalGravityLogged, Conditioned, ConditioningTempSrmColorLogged, Kegged,
BrewhouseEfficiencyLogged

Metrics
-------
>These metrics, less Plato, are captured in **Batch**.
1. **Temperature** - Measured during mashing, boiling, cooling, fermentation, conditioning, kegging and refrigeration.
    1. Method: ***thermometer***.
2. **Plato: P ~ Specific Gravity: SG** See [Table](https://www.brewersfriend.com/plato-to-sg-conversion-chart/)
    1. P = ( -1 * 616.868 ) + ( 1111.14 * SG ) - ( 630.272 * SG ^ 2 ) + ( 135.997 * SG ^ 3 )
    2. SG = 1 + ( P / ( 258.6 - ( ( P / 258.2 ) * 227.1 ) ) )
3. **pH** - The pH balance of the mash.
    1. Method: ***pH meter***.
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

Review
------
>**Batch** contains the following ***review*** properties:
1. Appearance
2. Aroma
3. Taste
>Review ratings include: 1, 2 and 3 stars. Higher is better.

Persistence
-----------
>**Recipes** and **Batches** are stored as json files here:
```
~/.brewmeister/store/batches | recipes
```

Logging
-------
>**Logs** files are stored here:
```
~/.brewmeister/logs
```

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
* [Beer Advocate](https://www.beeradvocate.com/)

Note
----
1. ScalaFx property binding for case class / properties / controls doesn't appear to work:
```
  case class Person(name: String):
    val nameProperty = ObjectProperty(name)
    nameProperty.bind( Bindings.createObjectBinding(() => this.name, nameProperty) )

  val labelName = Label( context.labelName )
  val textFieldName = new TextField():
    text <==> model.selectedPerson.value.nameProperty
```
>Simple ScalaFx property binding does work, though. Currently using manual case class / property binding.

License
-------
>Copyright (c) [2024, 2025] [Objektwerks]

>Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    * http://www.apache.org/licenses/LICENSE-2.0

>Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.