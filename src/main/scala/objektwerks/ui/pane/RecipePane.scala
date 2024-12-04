package objektwerks.ui.pane

import org.controlsfx.control.RangeSlider

import scalafx.Includes.*
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.{Button, Label, ScrollPane}
import scalafx.scene.layout.{Priority, HBox, VBox}

import objektwerks.*
import objektwerks.ui.{Context, Model}
import objektwerks.ui.control.{ControlGrid, LabelButton, NonEmptyTextField}
import objektwerks.ui.control.DoubleTextField
import objektwerks.ui.dialog.{AdjunctsDialog, GrainsDialog, HopsDialog, TempRangeDurationDialog, VolumeDialog, YeastsDialog}

final class RecipePane(context: Context, model: Model) extends VBox:
  padding = Insets(3)

  // Model
  model.selectedRecipe.onChange { (_, _, newRecipe) =>
    saveButton.disable = false
    recipeToControls(newRecipe)
  }

  // Bindings
  def recipeToControls(recipe: Recipe): Unit =
    textFieldName.text = recipe.name
    textFieldStyle.text = recipe.style
    textFieldWater.text = recipe.water

    labelButtonVolume.text = s"${recipe.volume.value} ${recipe.volume.unit.toString}"
    labelButtonVolume.value = recipe.volume

    labelButtonGrains.text = s"${recipe.grains.map(_.name).mkString(", ")}"
    labelButtonGrains.value = recipe.grains

    labelButtonHops.text = s"${recipe.hops.map(_.name).mkString(", ")}"
    labelButtonHops.value = recipe.hops

    labelButtonAdjuncts.text = s"${recipe.adjuncts.map(_.name).mkString(", ")}"
    labelButtonAdjuncts.value = recipe.adjuncts

    labelButtonYeasts.text = s"${recipe.yeasts.map(_.name).mkString(", ")}"
    labelButtonYeasts.value = recipe.yeasts
    
    labelButtonMashingTempRangeDuration.text =
      s"${recipe.mashingTempRangeDuration.tempRange.low} - ${recipe.mashingTempRangeDuration.tempRange.high}, " +
      s"${recipe.mashingTempRangeDuration.duration} ${recipe.mashingTempRangeDuration.unit.toString}"
    labelButtonMashingTempRangeDuration.value = recipe.mashingTempRangeDuration

    textFieldPotentialMashExtract.text = recipe.potentialMashExtract.toString
    textFieldPotentialFermentableExtract.text = recipe.potentialFermentableExtract.toString
    
    labelButtonBoilingTempRangeDuration.text =
      s"${recipe.boilingTempRangeDuration.tempRange.low} - ${recipe.boilingTempRangeDuration.tempRange.high}, " +
      s"${recipe.boilingTempRangeDuration.duration} ${recipe.boilingTempRangeDuration.unit.toString}"
    labelButtonBoilingTempRangeDuration.value = recipe.boilingTempRangeDuration
    
    rangeSliderCoolingTempRange.setLowValue(recipe.coolingTempRange.low)
    rangeSliderCoolingTempRange.setHighValue(recipe.coolingTempRange.high)
    
    labelButtonFermentingTempRangeDuration.text =
      s"${recipe.fermentingTempRangeDuration.tempRange.low} - ${recipe.fermentingTempRangeDuration.tempRange.high}, " +
      s"${recipe.fermentingTempRangeDuration.duration} ${recipe.fermentingTempRangeDuration.unit.toString}"
    labelButtonFermentingTempRangeDuration.value = recipe.fermentingTempRangeDuration
    
    labelButtonConditioningTempRangeDuration.text =
      s"${recipe.conditioningTempRangeDuration.tempRange.low} - ${recipe.conditioningTempRangeDuration.tempRange.high}, " +
      s"${recipe.conditioningTempRangeDuration.duration} ${recipe.conditioningTempRangeDuration.unit.toString}"
    labelButtonConditioningTempRangeDuration.value = recipe.conditioningTempRangeDuration
    
    labelButtonKeggingTempRangeDuration.text =
      s"${recipe.keggingTempRangeDuration.tempRange.low} - ${recipe.keggingTempRangeDuration.tempRange.high}, " +
      s"${recipe.keggingTempRangeDuration.duration} ${recipe.keggingTempRangeDuration.unit.toString}"
    labelButtonKeggingTempRangeDuration.value = recipe.keggingTempRangeDuration

    textFieldPh.text = recipe.pH.toString

    rangeSliderOriginalGravity.setLowValue(recipe.originalGravityRange.low)
    rangeSliderOriginalGravity.setHighValue(recipe.originalGravityRange.high)

    rangeSliderFinalGravity.setLowValue(recipe.finalGravityRange.low)
    rangeSliderFinalGravity.setHighValue(recipe.finalGravityRange.high)

    rangeSliderSrmColor.setLowValue(recipe.srmColorRange.low)
    rangeSliderSrmColor.setHighValue(recipe.srmColorRange.high)

    rangeSliderIbuBitterness.setLowValue(recipe.ibuBitternessRange.low)
    rangeSliderIbuBitterness.setHighValue(recipe.ibuBitternessRange.high)

    rangeSliderAlcoholByVolume.setLowValue(recipe.alcoholByVolumeRange.low)
    rangeSliderAlcoholByVolume.setHighValue(recipe.alcoholByVolumeRange.high)

    rangeSliderAlcoholByWeight.setLowValue(recipe.alcoholByWeight.low)
    rangeSliderAlcoholByWeight.setHighValue(recipe.alcoholByWeight.high)

    rangeSliderCalories.setLowValue(recipe.calories.low)
    rangeSliderCalories.setHighValue(recipe.calories.high)

    rangeSliderMashEfficiency.setLowValue(recipe.mashEfficiency.low)
    rangeSliderMashEfficiency.setHighValue(recipe.mashEfficiency.high)

    rangeSliderBrewhouseEfficiency.setLowValue(recipe.brewhouseEfficiency.low)
    rangeSliderBrewhouseEfficiency.setHighValue(recipe.brewhouseEfficiency.high)

  def controlsToRecipe(recipe: Recipe): Recipe =
      recipe.copy(
        name = textFieldName.string,
        style = textFieldStyle.string,
        water = textFieldWater.string,
        volume = labelButtonVolume.value.value,
        grains = labelButtonGrains.value.value,
        hops = labelButtonHops.value.value,
        adjuncts = labelButtonAdjuncts.value.value,
        yeasts = labelButtonYeasts.value.value,
        mashingTempRangeDuration = labelButtonMashingTempRangeDuration.value.value,
        potentialMashExtract = textFieldPotentialMashExtract.double.format,
        boilingTempRangeDuration = labelButtonBoilingTempRangeDuration.value.value,
        coolingTempRange = IntRange( rangeSliderCoolingTempRange.getLowValue.toInt, rangeSliderCoolingTempRange.getHighValue.toInt ),
        fermentingTempRangeDuration = labelButtonFermentingTempRangeDuration.value.value,
        potentialFermentableExtract = textFieldPotentialFermentableExtract.double.format,
        conditioningTempRangeDuration = labelButtonConditioningTempRangeDuration.value.value,
        keggingTempRangeDuration = labelButtonKeggingTempRangeDuration.value.value,
        pH = textFieldPh.double.format,
        originalGravityRange = DoubleRange( rangeSliderOriginalGravity.getLowValue, rangeSliderOriginalGravity.getHighValue ),
        finalGravityRange = DoubleRange( rangeSliderFinalGravity.getLowValue, rangeSliderFinalGravity.getHighValue ),
        srmColorRange = IntRange( rangeSliderSrmColor.getLowValue.toInt, rangeSliderSrmColor.getHighValue.toInt ),
        ibuBitternessRange = IntRange( rangeSliderIbuBitterness.getLowValue.toInt, rangeSliderIbuBitterness.getHighValue.toInt ),
        alcoholByVolumeRange = DoubleRange( rangeSliderAlcoholByVolume.getLowValue, rangeSliderAlcoholByVolume.getHighValue ),
        alcoholByWeight = DoubleRange( rangeSliderAlcoholByWeight.getLowValue, rangeSliderAlcoholByWeight.getHighValue ),
        calories = IntRange( rangeSliderCalories.getLowValue.toInt, rangeSliderCalories.getHighValue.toInt ),
        mashEfficiency = IntRange( rangeSliderMashEfficiency.getLowValue.toInt, rangeSliderMashEfficiency.getHighValue.toInt ),
        brewhouseEfficiency = IntRange( rangeSliderBrewhouseEfficiency.getLowValue.toInt, rangeSliderBrewhouseEfficiency.getHighValue.toInt )
      )

  // Methods
  def save(): Unit =
    saveButton.disable = true // TODO

  // Controls
  val labelName = Label( context.labelName )
  val textFieldName = NonEmptyTextField()

  val labelStyle = Label( context.labelStyle )
  val textFieldStyle = NonEmptyTextField()

  val labelWater = Label( context.labelWater )
  val textFieldWater = NonEmptyTextField()

  val labelVolume = Label( context.labelVolume )
  val labelButtonVolume = new LabelButton[Volume]:
    buttonAction = () => {
      VolumeDialog(context, model.selectedRecipe.value.volume).showAndWait() match
        case Some(volume: Volume) => volume
        case _ => model.selectedRecipe.value.volume
    }

  val labelGrains = Label( context.labelGrains )
  val labelButtonGrains = new LabelButton[List[Grain]]:
    buttonAction = () => {
      GrainsDialog(context, model.selectedRecipe.value.grains.toArray).showAndWait() match
        case Some(grains: Array[Grain]) => grains.toList
        case _ => model.selectedRecipe.value.grains
    }
  
  val labelHops = Label( context.labelHops )
  val labelButtonHops = new LabelButton[List[Hop]]:
    buttonAction = () => {
      HopsDialog(context, model.selectedRecipe.value.hops.toArray).showAndWait() match
        case Some(hops: Array[Hop]) => hops.toList
        case _ => model.selectedRecipe.value.hops
    }

  val labelAdjuncts = Label( context.labelAdjuncts )
  val labelButtonAdjuncts = new LabelButton[List[Adjunct]]:
    buttonAction = () => {
      AdjunctsDialog(context, model.selectedRecipe.value.adjuncts.toArray).showAndWait() match
        case Some(adjuncts: Array[Adjunct]) => adjuncts.toList
        case _ => model.selectedRecipe.value.adjuncts
    }

  val labelYeasts = Label( context.labelYeasts )
  val labelButtonYeasts = new LabelButton[List[Yeast]]:
    buttonAction = () => {
      YeastsDialog(context, model.selectedRecipe.value.yeasts.toArray).showAndWait() match
        case Some(yeasts: Array[Yeast]) => yeasts.toList
        case _ => model.selectedRecipe.value.yeasts
    }

  val labelMashingTempRangeDuration = Label( context.labelMashingTempRangeDuration )
  val labelButtonMashingTempRangeDuration = new LabelButton[TempRangeDuration]:
    buttonAction = () => {
      TempRangeDurationDialog(context, model.selectedRecipe.value.mashingTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => model.selectedRecipe.value.mashingTempRangeDuration
    }

  val labelPotentialMashExtract = Label( context.labelPotentialMashExtract )
  val textFieldPotentialMashExtract = DoubleTextField()

  val labelBoilingTempRangeDuration = Label( context.labelBoilingTempRangeDuration )
  val labelButtonBoilingTempRangeDuration = new LabelButton[TempRangeDuration]:
    buttonAction = () => {
      TempRangeDurationDialog(context, model.selectedRecipe.value.boilingTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => model.selectedRecipe.value.boilingTempRangeDuration
    }

  val labelCoolingTempRange = Label( context.labelCoolingTempRange )
  val rangeSliderCoolingTempRange = new RangeSlider(67, 73, model.selectedRecipe.value.coolingTempRange.low, model.selectedRecipe.value.coolingTempRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1)

  val labelFermentingTempRangeDuration = Label( context.labelFermentingTempRangeDuration )
  val labelButtonFermentingTempRangeDuration = new LabelButton[TempRangeDuration]:
    buttonAction = () => {
      TempRangeDurationDialog(context, model.selectedRecipe.value.fermentingTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => model.selectedRecipe.value.fermentingTempRangeDuration
    }

  val labelPotentialFermentableExtract = Label( context.labelPotentialFermentableExtract )
  val textFieldPotentialFermentableExtract = DoubleTextField()

  val labelConditioningTempRangeDuration = Label( context.labelConditioningTempRangeDuration )
  val labelButtonConditioningTempRangeDuration = new LabelButton[TempRangeDuration]:
    buttonAction = () => {
      TempRangeDurationDialog(context, model.selectedRecipe.value.conditioningTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => model.selectedRecipe.value.conditioningTempRangeDuration
    }

  val labelKeggingTempRangeDuration = Label( context.labelKeggingTempRangeDuration )
  val labelButtonKeggingTempRangeDuration = new LabelButton[TempRangeDuration]:
    buttonAction = () => {
      TempRangeDurationDialog(context, model.selectedRecipe.value.keggingTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => model.selectedRecipe.value.keggingTempRangeDuration
    }

  val labelPh = Label( context.labelPh )
  val textFieldPh = DoubleTextField()

  val labelOriginalGravity = Label( context.labelOriginalGravity )
  val rangeSliderOriginalGravity = new RangeSlider(1.000, 1.100, model.selectedRecipe.value.originalGravityRange.low, model.selectedRecipe.value.originalGravityRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(0.01)

  val labelFinalGravity = Label( context.labelFinalGravity )
  val rangeSliderFinalGravity = new RangeSlider(1.000, 1.050, model.selectedRecipe.value.finalGravityRange.low, model.selectedRecipe.value.finalGravityRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(0.01)

  val labelSrmColor = Label( context.labelSrmColor )
  val rangeSliderSrmColor = new RangeSlider(1, 80, model.selectedRecipe.value.srmColorRange.low, model.selectedRecipe.value.srmColorRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1)

  val labelIbuBitterness = Label( context.labelIbuBitterness )
  val rangeSliderIbuBitterness = new RangeSlider(1, 80, model.selectedRecipe.value.ibuBitternessRange.low, model.selectedRecipe.value.ibuBitternessRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1)

  val labelAlcoholByVolume = Label( context.labelAlcoholByVolume )
  val rangeSliderAlcoholByVolume = new RangeSlider(1.0, 12.0, model.selectedRecipe.value.alcoholByVolumeRange.low, model.selectedRecipe.value.alcoholByVolumeRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1.0)

  val labelAlcoholByWeight = Label( context.labelAlcoholByWeight )
  val rangeSliderAlcoholByWeight = new RangeSlider(1.0, 12.0, model.selectedRecipe.value.alcoholByWeight.low, model.selectedRecipe.value.alcoholByWeight.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1.0)

  val labelCalories = Label( context.labelCalories )
  val rangeSliderCalories = new RangeSlider(50, 250, model.selectedRecipe.value.calories.low, model.selectedRecipe.value.calories.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(25)

  val labelMashEfficiency = Label( context.labelMashEfficiency )
  val rangeSliderMashEfficiency = new RangeSlider(50, 100, model.selectedRecipe.value.mashEfficiency.low, model.selectedRecipe.value.mashEfficiency.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(10)

  val labelBrewhouseEfficiency = Label( context.labelBrewhouseEfficiency )
  val rangeSliderBrewhouseEfficiency = new RangeSlider(50, 100, model.selectedRecipe.value.brewhouseEfficiency.low, model.selectedRecipe.value.brewhouseEfficiency.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(10)

  val labelCreated = Label( context.labelCreated )
  val labelFieldCreated = Label( model.selectedRecipe.value.created )

  val controls = List[(Label, Node)](
    labelName -> textFieldName,
    labelStyle -> textFieldStyle,
    labelWater -> textFieldWater,
    labelVolume -> labelButtonVolume,
    labelGrains -> labelButtonGrains,
    labelHops -> labelButtonHops,
    labelAdjuncts -> labelButtonAdjuncts,
    labelYeasts -> labelButtonYeasts,
    labelMashingTempRangeDuration -> labelButtonMashingTempRangeDuration,
    labelPotentialMashExtract -> textFieldPotentialMashExtract,
    labelBoilingTempRangeDuration -> labelButtonBoilingTempRangeDuration,
    labelCoolingTempRange -> rangeSliderCoolingTempRange,
    labelFermentingTempRangeDuration -> labelButtonFermentingTempRangeDuration,
    labelPotentialFermentableExtract -> textFieldPotentialFermentableExtract,
    labelConditioningTempRangeDuration -> labelButtonConditioningTempRangeDuration,
    labelKeggingTempRangeDuration -> labelButtonKeggingTempRangeDuration,
    labelPh -> textFieldPh,
    labelOriginalGravity -> rangeSliderOriginalGravity,
    labelFinalGravity -> rangeSliderFinalGravity,
    labelSrmColor -> rangeSliderSrmColor,
    labelIbuBitterness -> rangeSliderIbuBitterness,
    labelAlcoholByVolume -> rangeSliderAlcoholByVolume,
    labelAlcoholByWeight -> rangeSliderAlcoholByWeight,
    labelCalories -> rangeSliderCalories,
    labelMashEfficiency -> rangeSliderMashEfficiency,
    labelBrewhouseEfficiency -> rangeSliderBrewhouseEfficiency,
    labelCreated -> labelFieldCreated
  )

  val controlsGrid = ControlGrid(controls)
  HBox.setHgrow(controlsGrid, Priority.Always)

  val scrollPaneControls = new ScrollPane:
    content = controlsGrid

  val saveButton = new Button:
    graphic = context.saveImageView
    text = context.buttonSave
    disable = true
    onAction = { _ => save() }

  val buttonBar = new HBox:
    spacing = 6
    padding = Insets(3)
    children = List(saveButton)

  children = List(scrollPaneControls, buttonBar)

  VBox.setVgrow(this, Priority.Always)