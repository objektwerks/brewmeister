Brewmeister
-----------
>Brewing process simulation using ScalaFx, ScalikeJdbc, Jsoniter, JoddMail, Helidon, Ox, PostgreSql and Scala 3.

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
13. **Packaging** - Bottle ( carbonate with priming sugar ) or keg ( force carbonate ) the ***fermented and conditioned wort***. Let condition for 2 weeks at required temperature. Then refrigerate.
>Some beer recipes will require adjustments to this process.

Recipe
------
* Recipe 1 -> * Malt | Hop | Adjunct | Result
* Recipe 1 -> 1 Style | Water | Yeast

Actors
------
* Sanitizer, Preparer, Malter, Miller, Masher, Lauter, Sparger, Boiler, Cooler, Whirlpooler, Fermenter, Conditioner, Packager

Commands
--------
* Sanitize, Prepare, Malt, Mill, Mash, Lauter, Sparge, Boil, Cool, Whirlpool, Ferment, Condition, Package

Events
------
* Sanitized, Prepared, Malted, Milled, Mashed, Lautered, Sparged, Boiled, Cooled, Whirlpooled, Fermented, Conditioned, Packaged

Metrics
-------
1. **Original Gravity: OG** - Origianl Gravity measures the gravity of wort before fermentation.
    1. Use a Hydrometer to measure OG before fermentation.
2. **Final Gravity: FG** - Final Gravity measures the gravity of wort after fermentation.
    1. Use a Hydrometer to measure FG after fermentation.
3. **Color: SRM** - Standard Reference Method/SRM/Beer Color is the color of your beer.
    1. Malt Color Units: MCU = (Weight of grain in lbs) * (Color of grain in degrees lovibond) / (volume in gallons)
    2. Color: SRM = 1.4922 * (MCU * 0.6859)
4. **Hop Bitterness: IBU** - International Bittering Units is the measure of bitterness of beer that comes from the boiling of hops.
    1. Hop Utilization(%) = Hop Alpha Acid(%) * Hop Weight(oz)) / Hop Volume(gallons)
    2. IBU = Hop Weight(oz) * Hop Alpha Acid(%) * Hop Utilization(%) / 7.25
5. **Alcohol: ABV** - Measured by volume, the amount of alcohol that was produced in your beer during fermentation.
    1. ABV = (OG - FG) * 131
6. **Alcohol: ABW** - Measured by weight, the amount of alcohol that was produced in your beer during fermentation.
    1. ABW = (0.79 * ABV) / FG
7. **Calories** - The number of calories in your beer.
    1. Volume = Beer bottle in ounces(oz).
    2. Alcohol Calories = (OG - FG) * 7.5
    3. Carbohydrate Calories = (FG * 13) * Volume
    4. Calories = Alcohol Calories + Carbohydrate Calories
8. **Mash Efficiency: ME** - Mash Efficiency calculates the percentage of fermentable extract extracted during the mash and sparging steps.
    1. Actual Extract = The amount of fermentable extract collected from the mash tun.
    2. Potential Extract = The maximum extract potential of the grain bill, calculated based on the malt’s extract potential and the grain bill’s weight and volume.
    3. ME(%) = (Actual Extract / Potential Extract) * 100
9. **Brewhouse Efficiency: BE** - Brewhouse Efficiency accounts for losses throughout the entire brewing process.
    1. Actual Fermentable Extract = The amount of fermentable extract collected from the brewhouse.
    2. Ideal Fermentable Extract = The maximum extract potential of the grain bill, assuming 100% efficiency throughout the brewing process.
    3. BE(%) = (Actual Fermentable Extract / Ideal Fermentable Extract) * 100

Resources
---------
* [Doc's Brewing Glossary](https://docmckee.com/beer/)
* [Brewing Fundamentals](https://beerconnoisseur.com/articles/beer-101-fundamental-steps-brewing)
* [Brulosophy](https://brulosophy.com/)
* [Beer XML](https://www.beerxml.com/)