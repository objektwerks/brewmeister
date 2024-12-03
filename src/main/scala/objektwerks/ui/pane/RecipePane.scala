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

final class RecipePane(context: Context, model: Model) extends ScrollPane:
  padding = Insets(3)

  val recipe = model.selectedRecipe

  model.selectedRecipe.onChange { (_, _, newRecipe) =>
    textFieldName.text = newRecipe.name
    textFieldStyle.text = newRecipe.style
    textFieldWater.text = newRecipe.water

    labelButtonVolume.text = s"${recipe.value.volume.value} ${recipe.value.volume.unit.toString}"
    labelButtonVolume.value = recipe.value.volume

    labelButtonGrains.text = s"${recipe.value.grains.map(_.name).mkString(", ")}"
    labelButtonGrains.value = recipe.value.grains

    labelButtonHops.text = s"${recipe.value.hops.map(_.name).mkString(", ")}"
    labelButtonHops.value = recipe.value.hops

    labelButtonAdjuncts.text = s"${recipe.value.adjuncts.map(_.name).mkString(", ")}"
    labelButtonAdjuncts.value = recipe.value.adjuncts

    labelButtonYeasts.text = s"${recipe.value.yeasts.map(_.name).mkString(", ")}"
    labelButtonYeasts.value = recipe.value.yeasts
    
    labelButtonMashingTempRangeDuration.text =
      s"${recipe.value.mashingTempRangeDuration.tempRange.low} - ${recipe.value.mashingTempRangeDuration.tempRange.high}, " +
      s"${recipe.value.mashingTempRangeDuration.duration} ${recipe.value.mashingTempRangeDuration.unit.toString}"
    labelButtonMashingTempRangeDuration.value = recipe.value.mashingTempRangeDuration
    
    labelButtonBoilingTempRangeDuration.text =
      s"${recipe.value.boilingTempRangeDuration.tempRange.low} - ${recipe.value.boilingTempRangeDuration.tempRange.high}, " +
      s"${recipe.value.boilingTempRangeDuration.duration} ${recipe.value.boilingTempRangeDuration.unit.toString}"
    labelButtonBoilingTempRangeDuration.value = recipe.value.boilingTempRangeDuration
    
    rangeSliderCoolingTempRange.setLowValue(recipe.value.coolingTempRange.low)
    rangeSliderCoolingTempRange.setHighValue(recipe.value.coolingTempRange.high)
    
    labelButtonFermentingTempRangeDuration.text =
      s"${recipe.value.fermentingTempRangeDuration.tempRange.low} - ${recipe.value.fermentingTempRangeDuration.tempRange.high}, " +
      s"${recipe.value.fermentingTempRangeDuration.duration} ${recipe.value.fermentingTempRangeDuration.unit.toString}"
    labelButtonFermentingTempRangeDuration.value = recipe.value.fermentingTempRangeDuration
    
    labelButtonConditioningTempRangeDuration.text =
      s"${recipe.value.conditioningTempRangeDuration.tempRange.low} - ${recipe.value.conditioningTempRangeDuration.tempRange.high}, " +
      s"${recipe.value.conditioningTempRangeDuration.duration} ${recipe.value.conditioningTempRangeDuration.unit.toString}"
    labelButtonConditioningTempRangeDuration.value = recipe.value.conditioningTempRangeDuration
    
    labelButtonKeggingTempRangeDuration.text =
      s"${recipe.value.keggingTempRangeDuration.tempRange.low} - ${recipe.value.keggingTempRangeDuration.tempRange.high}, " +
      s"${recipe.value.keggingTempRangeDuration.duration} ${recipe.value.keggingTempRangeDuration.unit.toString}"
    labelButtonKeggingTempRangeDuration.value = recipe.value.keggingTempRangeDuration

    rangeSliderOriginalGravity.setLowValue(recipe.value.originalGravity.low)
    rangeSliderOriginalGravity.setHighValue(recipe.value.originalGravity.high)

    rangeSliderFinalGravity.setLowValue(recipe.value.finalGravity.low)
    rangeSliderFinalGravity.setHighValue(recipe.value.finalGravity.high)

    rangeSliderSrmColor.setLowValue(recipe.value.srmColor.low)
    rangeSliderSrmColor.setHighValue(recipe.value.srmColor.high)

    rangeSliderIbuBitterness.setLowValue(recipe.value.ibuBitterness.low)
    rangeSliderIbuBitterness.setHighValue(recipe.value.ibuBitterness.high)

    rangeSliderAlcoholByVolume.setLowValue(recipe.value.alcoholByVolume.low)
    rangeSliderAlcoholByVolume.setHighValue(recipe.value.alcoholByVolume.high)

    rangeSliderAlcoholByWeight.setLowValue(recipe.value.alcoholByWeight.low)
    rangeSliderAlcoholByWeight.setHighValue(recipe.value.alcoholByWeight.high)

    rangeSliderCalories.setLowValue(recipe.value.calories.low)
    rangeSliderCalories.setHighValue(recipe.value.calories.high)

    rangeSliderMashEfficiency.setLowValue(recipe.value.mashEfficiency.low)
    rangeSliderMashEfficiency.setHighValue(recipe.value.mashEfficiency.high)

    rangeSliderBrewhouseEfficiency.setLowValue(recipe.value.brewhouseEfficiency.low)
    rangeSliderBrewhouseEfficiency.setHighValue(recipe.value.brewhouseEfficiency.high)

    textFieldPotentialMashExtract.text = recipe.value.potentialMashExtract.toString
    textFieldPotentialFermentableExtract.text = recipe.value.potentialFermentableExtract.toString
    textFieldPh.text = recipe.value.pH.toString
  }

  val labelName = Label( context.labelName )
  val textFieldName = NonEmptyTextField()

  val labelStyle = Label( context.labelStyle )
  val textFieldStyle = NonEmptyTextField()

  val labelWater = Label( context.labelWater )
  val textFieldWater = NonEmptyTextField()

  val labelVolume = Label( context.labelVolume )
  val labelButtonVolume = new LabelButton[Volume]:
    buttonAction = () => {
      VolumeDialog(context, recipe.value.volume).showAndWait() match
        case Some(volume: Volume) => volume
        case _ => recipe.value.volume
    }

  val labelGrains = Label( context.labelGrains )
  val labelButtonGrains = new LabelButton[List[Grain]]:
    buttonAction = () => {
      GrainsDialog(context, recipe.value.grains.toArray).showAndWait() match
        case Some(grains: Array[Grain]) => grains.toList
        case _ => recipe.value.grains
    }
  
  val labelHops = Label( context.labelHops )
  val labelButtonHops = new LabelButton[List[Hop]]:
    buttonAction = () => {
      HopsDialog(context, recipe.value.hops.toArray).showAndWait() match
        case Some(hops: Array[Hop]) => hops.toList
        case _ => recipe.value.hops
    }

  val labelAdjuncts = Label( context.labelAdjuncts )
  val labelButtonAdjuncts = new LabelButton[List[Adjunct]]:
    buttonAction = () => {
      AdjunctsDialog(context, recipe.value.adjuncts.toArray).showAndWait() match
        case Some(adjuncts: Array[Adjunct]) => adjuncts.toList
        case _ => recipe.value.adjuncts
    }

  val labelYeasts = Label( context.labelYeasts )
  val labelButtonYeasts = new LabelButton[List[Yeast]]:
    buttonAction = () => {
      YeastsDialog(context, recipe.value.yeasts.toArray).showAndWait() match
        case Some(yeasts: Array[Yeast]) => yeasts.toList
        case _ => recipe.value.yeasts
    }

  val labelMashingTempRangeDuration = Label( context.labelMashingTempRangeDuration )
  val labelButtonMashingTempRangeDuration = new LabelButton[TempRangeDuration]:
    buttonAction = () => {
      TempRangeDurationDialog(context, recipe.value.mashingTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => recipe.value.mashingTempRangeDuration
    }

  val labelPotentialMashExtract = Label( context.labelPotentialMashExtract )
  val textFieldPotentialMashExtract = DoubleTextField()

  val labelBoilingTempRangeDuration = Label( context.labelBoilingTempRangeDuration )
  val labelButtonBoilingTempRangeDuration = new LabelButton[TempRangeDuration]:
    buttonAction = () => {
      TempRangeDurationDialog(context, recipe.value.boilingTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => recipe.value.boilingTempRangeDuration
    }

  val labelCoolingTempRange = Label( context.labelCoolingTempRange )
  val rangeSliderCoolingTempRange = new RangeSlider(67, 73, recipe.value.coolingTempRange.low, recipe.value.coolingTempRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1)

  val labelFermentingTempRangeDuration = Label( context.labelFermentingTempRangeDuration )
  val labelButtonFermentingTempRangeDuration = new LabelButton[TempRangeDuration]:
    buttonAction = () => {
      TempRangeDurationDialog(context, recipe.value.fermentingTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => recipe.value.fermentingTempRangeDuration
    }

  val labelPotentialFermentableExtract = Label( context.labelPotentialFermentableExtract )
  val textFieldPotentialFermentableExtract = DoubleTextField()

  val labelConditioningTempRangeDuration = Label( context.labelConditioningTempRangeDuration )
  val labelButtonConditioningTempRangeDuration = new LabelButton[TempRangeDuration]:
    buttonAction = () => {
      TempRangeDurationDialog(context, recipe.value.conditioningTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => recipe.value.conditioningTempRangeDuration
    }

  val labelKeggingTempRangeDuration = Label( context.labelKeggingTempRangeDuration )
  val labelButtonKeggingTempRangeDuration = new LabelButton[TempRangeDuration]:
    buttonAction = () => {
      TempRangeDurationDialog(context, recipe.value.keggingTempRangeDuration).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) => tempRangeDuration
        case _ => recipe.value.keggingTempRangeDuration
    }

  val labelPh = Label( context.labelPh )
  val textFieldPh = DoubleTextField()

  val labelOriginalGravity = Label( context.labelOriginalGravity )
  val rangeSliderOriginalGravity = new RangeSlider(1.000, 1.100, recipe.value.originalGravity.low, recipe.value.originalGravity.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(0.10)

  val labelFinalGravity = Label( context.labelFinalGravity )
  val rangeSliderFinalGravity = new RangeSlider(1.000, 1.050, recipe.value.finalGravity.low, recipe.value.finalGravity.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(0.10)

  val labelSrmColor = Label( context.labelSrmColor )
  val rangeSliderSrmColor = new RangeSlider(1, 80, recipe.value.srmColor.low, recipe.value.srmColor.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(10)

  val labelIbuBitterness = Label( context.labelIbuBitterness )
  val rangeSliderIbuBitterness = new RangeSlider(1, 80, recipe.value.ibuBitterness.low, recipe.value.ibuBitterness.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(10)

  val labelAlcoholByVolume = Label( context.labelAlcoholByVolume )
  val rangeSliderAlcoholByVolume = new RangeSlider(1.0, 12.0, recipe.value.alcoholByVolume.low, recipe.value.alcoholByVolume.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1.0)

  val labelAlcoholByWeight = Label( context.labelAlcoholByWeight )
  val rangeSliderAlcoholByWeight = new RangeSlider(1.0, 12.0, recipe.value.alcoholByWeight.low, recipe.value.alcoholByWeight.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1.0)

  val labelCalories = Label( context.labelCalories )
  val rangeSliderCalories = new RangeSlider(50, 250, recipe.value.calories.low, recipe.value.calories.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(50)

  val labelMashEfficiency = Label( context.labelMashEfficiency )
  val rangeSliderMashEfficiency = new RangeSlider(50, 100, recipe.value.mashEfficiency.low, recipe.value.mashEfficiency.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(10)

  val labelBrewhouseEfficiency = Label( context.labelBrewhouseEfficiency )
  val rangeSliderBrewhouseEfficiency = new RangeSlider(50, 100, recipe.value.brewhouseEfficiency.low, recipe.value.brewhouseEfficiency.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(10)

  val labelCreated = Label( context.labelCreated )
  val labelFieldCreated = Label( recipe.value.created )

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

  val saveButton = new Button:
    graphic = context.saveImageView
    text = context.buttonSave
    disable = true
    onAction = { _ => save() }

  val buttonBar = new HBox:
    spacing = 6
    padding = Insets(6)
    children = List(saveButton)

  content = new VBox:
    children = List( ControlGrid(controls), buttonBar )

  def save(): Unit = ???
  
  VBox.setVgrow(this, Priority.Always)