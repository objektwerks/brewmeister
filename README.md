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
>Some beer recipes might require adjustments to this process.

Recipe
------
* Recipe 1 -> * Hop | Fermentable | Adjunct | Result
* Recipe 1 -> 1 Style | Water | Yeast

Actors
------
* Sanitizer, Preparer, Malter, Miller, Masher, Lauter, Sparger, Boiler, Cooler, Whirlpooler, Fermenter, Conditioner, Packager

Commands
--------
* Sanitize, Prepare, Malt, Mill, Mash, Lauter, Sparge, Boil, Cool, Whirlpool, Ferment, Condition, Package

Events
------
*Sanitized, Prepared, Malted, Milled, Mashed, Lautered, Sparged, Boiled, Cooled, Whirlpooled, Fermented, Conditioned, Packaged

Metrics
-------
1. **Original Gravity: OG** - Origianl Gravity is the specific gravity of your wort before fermentation.
2. **Final Gravity: FG** - Final Gravity is the specific gravity of your wort after fermentation has completed.
3. **Color: SRM** - Standard Reference Method/SRM/Beer Color is the color of your homebrew.
4. **Hop Bitterness: IBU** - International Bittering Units is the measure of bitterness of homebrew that comes from the boiling of hops.
5. **Alcohol: ABV** -
6. **Calories** -

Resources
---------
* [Brewing Fundamentals](https://beerconnoisseur.com/articles/beer-101-fundamental-steps-brewing)
* [Brulosophy](https://brulosophy.com/)
* [Beer XML](https://www.beerxml.com/)