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
  model.selectedRecipe.onChange { (_, _, selectedRecipe) =>
    recipeToControls(selectedRecipe)
    setSave()
  }

  model.observableRecipes.onChange { (_, _) =>
    setSave()
  }

  // Methods
  def save(): Unit =
    val saved = model.save( controlsToRecipe(model.selectedRecipe.value) )
    buttonSave.disable = saved

  def setSave(): Unit =
    if model.observableRecipes.isEmpty then
      buttonSave.disable = true
    else
      buttonSave.disable = false

  def disableSave(): Unit = buttonSave.disable = true

  def enableSave(): Unit = buttonSave.disable = false

  // Bindings
  def recipeToControls(recipe: Recipe): Unit =
    textName.text = recipe.name
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
    
    rangeSliderCoolingTemp.setLowValue(recipe.coolingTempRange.low)
    rangeSliderCoolingTemp.setHighValue(recipe.coolingTempRange.high)
    
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

    rangeSliderAlcoholByWeight.setLowValue(recipe.alcoholByWeightRange.low)
    rangeSliderAlcoholByWeight.setHighValue(recipe.alcoholByWeightRange.high)

    rangeSliderCalories.setLowValue(recipe.calorieRange.low)
    rangeSliderCalories.setHighValue(recipe.calorieRange.high)

    rangeSliderMashEfficiency.setLowValue(recipe.mashEfficiencyRange.low)
    rangeSliderMashEfficiency.setHighValue(recipe.mashEfficiencyRange.high)

    rangeSliderBrewhouseEfficiency.setLowValue(recipe.brewhouseEfficiencyRange.low)
    rangeSliderBrewhouseEfficiency.setHighValue(recipe.brewhouseEfficiencyRange.high)

  def controlsToRecipe(recipe: Recipe): Recipe =
      recipe.copy(
        name = textName.text.value,
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
        coolingTempRange = IntRange( rangeSliderCoolingTemp.getLowValue.toInt, rangeSliderCoolingTemp.getHighValue.toInt ),
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
        alcoholByWeightRange = DoubleRange( rangeSliderAlcoholByWeight.getLowValue, rangeSliderAlcoholByWeight.getHighValue ),
        calorieRange = IntRange( rangeSliderCalories.getLowValue.toInt, rangeSliderCalories.getHighValue.toInt ),
        mashEfficiencyRange = IntRange( rangeSliderMashEfficiency.getLowValue.toInt, rangeSliderMashEfficiency.getHighValue.toInt ),
        brewhouseEfficiencyRange = IntRange( rangeSliderBrewhouseEfficiency.getLowValue.toInt, rangeSliderBrewhouseEfficiency.getHighValue.toInt )
      )

  // Controls
  val labelName = Label( context.labelName )
  val textName = Label("")

  val labelStyle = Label( context.labelStyle )
  val textFieldStyle = new NonEmptyTextField():
    text.onChange { (_, _, _) => enableSave() }

  val labelWater = Label( context.labelWater )
  val textFieldWater = new NonEmptyTextField():
    text.onChange { (_, _, _) => enableSave() }

  val labelVolume = Label( context.labelVolume )
  val labelButtonVolume = new LabelButton[Volume]:
    text.onChange { (_, _, _) => enableSave() }
    value = model.selectedRecipe.value.volume
    buttonAction = () => {
      VolumeDialog(context, value.value).showAndWait() match
        case Some(volume: Volume) =>
          text = s"${volume.value} ${volume.unit}"
          volume
        case _ => value.value
    }

  val labelGrains = Label( context.labelGrains )
  val labelButtonGrains = new LabelButton[List[Grain]]:
    text.onChange { (_, _, _) => enableSave() }
    value = model.selectedRecipe.value.grains
    buttonAction = () => {
      GrainsDialog(context, value.value.toArray).showAndWait() match
        case Some(grains: Array[Grain]) =>
          text = s"${grains.map(_.name).mkString(", ")}"
          grains.toList
        case _ => value.value
    }
  
  val labelHops = Label( context.labelHops )
  val labelButtonHops = new LabelButton[List[Hop]]:
    text.onChange { (_, _, _) => enableSave() }
    value = model.selectedRecipe.value.hops
    buttonAction = () => {
      HopsDialog(context, value.value.toArray).showAndWait() match
        case Some(hops: Array[Hop]) =>
          text = s"${hops.map(_.name).mkString(", ")}"
          hops.toList
        case _ => value.value
    }

  val labelAdjuncts = Label( context.labelAdjuncts )
  val labelButtonAdjuncts = new LabelButton[List[Adjunct]]:
    text.onChange { (_, _, _) => enableSave() }
    value = model.selectedRecipe.value.adjuncts
    buttonAction = () => {
      AdjunctsDialog(context, value.value.toArray).showAndWait() match
        case Some(adjuncts: Array[Adjunct]) =>
          text = s"${adjuncts.map(_.name).mkString(", ")}"
          adjuncts.toList
        case _ => value.value
    }

  val labelYeasts = Label( context.labelYeasts )
  val labelButtonYeasts = new LabelButton[List[Yeast]]:
    text.onChange { (_, _, _) => enableSave() }
    value = model.selectedRecipe.value.yeasts
    buttonAction = () => {
      YeastsDialog(context, value.value.toArray).showAndWait() match
        case Some(yeasts: Array[Yeast]) =>
          text = s"${yeasts.map(_.name).mkString(", ")}"
          yeasts.toList
        case _ => value.value
    }

  val labelMashingTempRangeDuration = Label( context.labelMashingTempRangeDuration )
  val labelButtonMashingTempRangeDuration = new LabelButton[TempRangeDuration]:
    text.onChange { (_, _, _) => enableSave() }
    value = model.selectedRecipe.value.mashingTempRangeDuration
    buttonAction = () => {
      TempRangeDurationDialog(context, value.value).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) =>
          text = s"${tempRangeDuration.tempRange.low} - ${tempRangeDuration.tempRange.high}, " +
                 s"${tempRangeDuration.duration} ${tempRangeDuration.unit.toString}"
          tempRangeDuration
        case _ => value.value
    }

  val labelPotentialMashExtract = Label( context.labelPotentialMashExtract )
  val textFieldPotentialMashExtract = new DoubleTextField():
    text.onChange { (_, _, _) => enableSave() }

  val labelBoilingTempRangeDuration = Label( context.labelBoilingTempRangeDuration )
  val labelButtonBoilingTempRangeDuration = new LabelButton[TempRangeDuration]:
    text.onChange { (_, _, _) => enableSave() }
    value = model.selectedRecipe.value.boilingTempRangeDuration
    buttonAction = () => {
      TempRangeDurationDialog(context, value.value).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) =>
          text = s"${tempRangeDuration.tempRange.low} - ${tempRangeDuration.tempRange.high}, " +
                 s"${tempRangeDuration.duration} ${tempRangeDuration.unit.toString}"
          tempRangeDuration
        case _ => value.value
    }

  val labelCoolingTempRange = Label( context.labelCoolingTempRange )
  val rangeSliderCoolingTemp = new RangeSlider(67, 73, model.selectedRecipe.value.coolingTempRange.low, model.selectedRecipe.value.coolingTempRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1)
    lowValueProperty.onChange { (_, _, _) => enableSave() }
    highValueProperty.onChange { (_, _, _) => enableSave() }

  val labelFermentingTempRangeDuration = Label( context.labelFermentingTempRangeDuration )
  val labelButtonFermentingTempRangeDuration = new LabelButton[TempRangeDuration]:
    value = model.selectedRecipe.value.fermentingTempRangeDuration
    buttonAction = () => {
      TempRangeDurationDialog(context, value.value).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) =>
          text = s"${tempRangeDuration.tempRange.low} - ${tempRangeDuration.tempRange.high}, " +
                 s"${tempRangeDuration.duration} ${tempRangeDuration.unit.toString}"
          tempRangeDuration
        case _ => value.value
    }

  val labelPotentialFermentableExtract = Label( context.labelPotentialFermentableExtract )
  val textFieldPotentialFermentableExtract = new DoubleTextField():
    text.onChange { (_, _, _) => enableSave() }

  val labelConditioningTempRangeDuration = Label( context.labelConditioningTempRangeDuration )
  val labelButtonConditioningTempRangeDuration = new LabelButton[TempRangeDuration]:
    text.onChange { (_, _, _) => enableSave() }
    value = model.selectedRecipe.value.conditioningTempRangeDuration
    buttonAction = () => {
      TempRangeDurationDialog(context, value.value).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) =>
          text = s"${tempRangeDuration.tempRange.low} - ${tempRangeDuration.tempRange.high}, " +
                 s"${tempRangeDuration.duration} ${tempRangeDuration.unit.toString}"
          tempRangeDuration
        case _ => value.value
    }

  val labelKeggingTempRangeDuration = Label( context.labelKeggingTempRangeDuration )
  val labelButtonKeggingTempRangeDuration = new LabelButton[TempRangeDuration]:
    text.onChange { (_, _, _) => enableSave() }
    value = model.selectedRecipe.value.keggingTempRangeDuration
    buttonAction = () => {
      TempRangeDurationDialog(context, value.value).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) =>
          text = s"${tempRangeDuration.tempRange.low} - ${tempRangeDuration.tempRange.high}, " +
                 s"${tempRangeDuration.duration} ${tempRangeDuration.unit.toString}"
          tempRangeDuration
        case _ => value.value
    }

  val labelPh = Label( context.labelPh )
  val textFieldPh = new DoubleTextField():
    text.onChange { (_, _, _) => enableSave() }

  val labelOriginalGravityRange = Label( context.labelOriginalGravityRange )
  val rangeSliderOriginalGravity = new RangeSlider(1.000, 1.100, model.selectedRecipe.value.originalGravityRange.low, model.selectedRecipe.value.originalGravityRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(0.01)
    lowValueProperty.onChange { (_, _, _) => enableSave() }
    highValueProperty.onChange { (_, _, _) => enableSave() }

  val labelFinalGravityRange = Label( context.labelFinalGravityRange )
  val rangeSliderFinalGravity = new RangeSlider(1.000, 1.050, model.selectedRecipe.value.finalGravityRange.low, model.selectedRecipe.value.finalGravityRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(0.01)
    lowValueProperty.onChange { (_, _, _) => enableSave() }
    highValueProperty.onChange { (_, _, _) => enableSave() }

  val labelSrmColorRange = Label( context.labelSrmColorRange )
  val rangeSliderSrmColor = new RangeSlider(1, 80, model.selectedRecipe.value.srmColorRange.low, model.selectedRecipe.value.srmColorRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1)
    lowValueProperty.onChange { (_, _, _) => enableSave() }
    highValueProperty.onChange { (_, _, _) => enableSave() }

  val labelIbuBitternessRange = Label( context.labelIbuBitternessRange )
  val rangeSliderIbuBitterness = new RangeSlider(1, 80, model.selectedRecipe.value.ibuBitternessRange.low, model.selectedRecipe.value.ibuBitternessRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1)
    lowValueProperty.onChange { (_, _, _) => enableSave() }
    highValueProperty.onChange { (_, _, _) => enableSave() }

  val labelAlcoholByVolumeRange = Label( context.labelAlcoholByVolumeRange )
  val rangeSliderAlcoholByVolume = new RangeSlider(1.0, 12.0, model.selectedRecipe.value.alcoholByVolumeRange.low, model.selectedRecipe.value.alcoholByVolumeRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1.0)

  val labelAlcoholByWeightRange = Label( context.labelAlcoholByWeightRange )
  val rangeSliderAlcoholByWeight = new RangeSlider(1.0, 12.0, model.selectedRecipe.value.alcoholByWeightRange.low, model.selectedRecipe.value.alcoholByWeightRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(1.0)

  val labelCalorieRange = Label( context.labelCalorieRange )
  val rangeSliderCalories = new RangeSlider(50, 250, model.selectedRecipe.value.calorieRange.low, model.selectedRecipe.value.calorieRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(25)

  val labelMashEfficiencyRange = Label( context.labelMashEfficiencyRange )
  val rangeSliderMashEfficiency = new RangeSlider(50, 100, model.selectedRecipe.value.mashEfficiencyRange.low, model.selectedRecipe.value.mashEfficiencyRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(10)

  val labelBrewhouseEfficiencyRange = Label( context.labelBrewhouseEfficiencyRange )
  val rangeSliderBrewhouseEfficiency = new RangeSlider(50, 100, model.selectedRecipe.value.brewhouseEfficiencyRange.low, model.selectedRecipe.value.brewhouseEfficiencyRange.high):
    setShowTickMarks(true)
    setShowTickLabels(true)
    setBlockIncrement(10)

  val labelCreated = Label( context.labelCreated )
  val labelFieldCreated = Label( model.selectedRecipe.value.created )

  val controls = List[(Label, Node)](
    labelName -> textName,
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
    labelCoolingTempRange -> rangeSliderCoolingTemp,
    labelFermentingTempRangeDuration -> labelButtonFermentingTempRangeDuration,
    labelPotentialFermentableExtract -> textFieldPotentialFermentableExtract,
    labelConditioningTempRangeDuration -> labelButtonConditioningTempRangeDuration,
    labelKeggingTempRangeDuration -> labelButtonKeggingTempRangeDuration,
    labelPh -> textFieldPh,
    labelOriginalGravityRange -> rangeSliderOriginalGravity,
    labelFinalGravityRange -> rangeSliderFinalGravity,
    labelSrmColorRange -> rangeSliderSrmColor,
    labelIbuBitternessRange -> rangeSliderIbuBitterness,
    labelAlcoholByVolumeRange -> rangeSliderAlcoholByVolume,
    labelAlcoholByWeightRange -> rangeSliderAlcoholByWeight,
    labelCalorieRange -> rangeSliderCalories,
    labelMashEfficiencyRange -> rangeSliderMashEfficiency,
    labelBrewhouseEfficiencyRange -> rangeSliderBrewhouseEfficiency,
    labelCreated -> labelFieldCreated
  )

  val scrollPaneControls = new ScrollPane:
    content = ControlGrid(controls)

  // Buttons
  val buttonSave = new Button:
    graphic = context.imageViewSave
    tooltip = context.tooltipSave
    disable = true
    onAction = { _ => save() }

  val buttonBar = new HBox:
    spacing = 6
    padding = Insets(3)
    children = List(buttonSave)

  // Content
  children = List(scrollPaneControls, buttonBar)

  VBox.setVgrow(this, Priority.Always)