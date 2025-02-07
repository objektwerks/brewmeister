package objektwerks.ui.pane

import scalafx.geometry.{HPos, Insets}
import scalafx.scene.Node
import scalafx.scene.control.{Button, Label, ScrollPane}
import scalafx.scene.layout.{ColumnConstraints, Priority, HBox, VBox}

import objektwerks.*
import objektwerks.ui.{Context, Model}
import objektwerks.ui.control.{ControlGrid, Format, LabelButton, LabelRangeSlider, NonEmptyTextField}
import objektwerks.ui.control.DoubleTextField
import objektwerks.ui.dialog.{AdjunctsDialog, GrainsDialog, HopsDialog, TempRangeDurationDialog, VolumeDialog, YeastsDialog}

final class RecipePane(context: Context, model: Model) extends VBox:
  padding = Insets(3)

  // Model
  model.selectedRecipe.onChange { (_, _, selectedRecipe) =>
    recipeToControls(selectedRecipe)
    disableSave()
  }

  // Methods
  def save(): Unit =
    model.save( controlsToRecipe(model.selectedRecipe.value) )
    buttonSave.disable = true

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
      s"${recipe.mashingTempRangeDuration.modelTempRange.low} - ${recipe.mashingTempRangeDuration.modelTempRange.high}, " +
      s"${recipe.mashingTempRangeDuration.duration} ${recipe.mashingTempRangeDuration.unit.toString}"
    labelButtonMashingTempRangeDuration.value = recipe.mashingTempRangeDuration

    textFieldPotentialMashExtract.text = recipe.potentialMashExtract.toString
    textFieldPotentialFermentableExtract.text = recipe.potentialFermentableExtract.toString
    
    labelButtonBoilingTempRangeDuration.text =
      s"${recipe.boilingTempRangeDuration.modelTempRange.low} - ${recipe.boilingTempRangeDuration.modelTempRange.high}, " +
      s"${recipe.boilingTempRangeDuration.duration} ${recipe.boilingTempRangeDuration.unit.toString}"
    labelButtonBoilingTempRangeDuration.value = recipe.boilingTempRangeDuration
    
    labelRangeSliderCoolingTemp.lowValue(recipe.coolingTempRange.low)
    labelRangeSliderCoolingTemp.highValue(recipe.coolingTempRange.high)
    
    labelButtonFermentingTempRangeDuration.text =
      s"${recipe.fermentingTempRangeDuration.modelTempRange.low} - ${recipe.fermentingTempRangeDuration.modelTempRange.high}, " +
      s"${recipe.fermentingTempRangeDuration.duration} ${recipe.fermentingTempRangeDuration.unit.toString}"
    labelButtonFermentingTempRangeDuration.value = recipe.fermentingTempRangeDuration
    
    labelButtonConditioningTempRangeDuration.text =
      s"${recipe.conditioningTempRangeDuration.modelTempRange.low} - ${recipe.conditioningTempRangeDuration.modelTempRange.high}, " +
      s"${recipe.conditioningTempRangeDuration.duration} ${recipe.conditioningTempRangeDuration.unit.toString}"
    labelButtonConditioningTempRangeDuration.value = recipe.conditioningTempRangeDuration
    
    labelButtonKeggingTempRangeDuration.text =
      s"${recipe.keggingTempRangeDuration.modelTempRange.low} - ${recipe.keggingTempRangeDuration.modelTempRange.high}, " +
      s"${recipe.keggingTempRangeDuration.duration} ${recipe.keggingTempRangeDuration.unit.toString}"
    labelButtonKeggingTempRangeDuration.value = recipe.keggingTempRangeDuration

    textFieldPh.text = recipe.pH.toString

    labelRangeSliderOriginalGravity.lowValue(recipe.originalGravityRange.low)
    labelRangeSliderOriginalGravity.highValue(recipe.originalGravityRange.high)

    labelRangeSliderFinalGravity.lowValue(recipe.finalGravityRange.low)
    labelRangeSliderFinalGravity.highValue(recipe.finalGravityRange.high)

    labelRangeSliderSrmColor.lowValue(recipe.srmColorRange.low)
    labelRangeSliderSrmColor.highValue(recipe.srmColorRange.high)

    labelRangeSliderIbuBitterness.lowValue(recipe.ibuBitternessRange.low)
    labelRangeSliderIbuBitterness.highValue(recipe.ibuBitternessRange.high)

    labelRangeSliderAlcoholByVolume.lowValue(recipe.alcoholByVolumeRange.low)
    labelRangeSliderAlcoholByVolume.highValue(recipe.alcoholByVolumeRange.high)

    labelRangeSliderAlcoholByWeight.lowValue(recipe.alcoholByWeightRange.low)
    labelRangeSliderAlcoholByWeight.highValue(recipe.alcoholByWeightRange.high)

    labelRangeSliderCalories.lowValue(recipe.calorieRange.low)
    labelRangeSliderCalories.highValue(recipe.calorieRange.high)

    labelRangeSliderMashEfficiency.lowValue(recipe.mashEfficiencyRange.low)
    labelRangeSliderMashEfficiency.highValue(recipe.mashEfficiencyRange.high)

    labelRangeSliderBrewhouseEfficiency.lowValue(recipe.brewhouseEfficiencyRange.low)
    labelRangeSliderBrewhouseEfficiency.highValue(recipe.brewhouseEfficiencyRange.high)

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
        coolingTempRange = IntRange( labelRangeSliderCoolingTemp.lowValue.toInt, labelRangeSliderCoolingTemp.highValue.toInt ),
        fermentingTempRangeDuration = labelButtonFermentingTempRangeDuration.value.value,
        potentialFermentableExtract = textFieldPotentialFermentableExtract.double.format,
        conditioningTempRangeDuration = labelButtonConditioningTempRangeDuration.value.value,
        keggingTempRangeDuration = labelButtonKeggingTempRangeDuration.value.value,
        pH = textFieldPh.double.format,
        originalGravityRange = DoubleRange( labelRangeSliderOriginalGravity.lowValue, labelRangeSliderOriginalGravity.highValue ),
        finalGravityRange = DoubleRange( labelRangeSliderFinalGravity.lowValue, labelRangeSliderFinalGravity.highValue ),
        srmColorRange = IntRange( labelRangeSliderSrmColor.lowValue.toInt, labelRangeSliderSrmColor.highValue.toInt ),
        ibuBitternessRange = IntRange( labelRangeSliderIbuBitterness.lowValue.toInt, labelRangeSliderIbuBitterness.highValue.toInt ),
        alcoholByVolumeRange = DoubleRange( labelRangeSliderAlcoholByVolume.lowValue, labelRangeSliderAlcoholByVolume.highValue ),
        alcoholByWeightRange = DoubleRange( labelRangeSliderAlcoholByWeight.lowValue, labelRangeSliderAlcoholByWeight.highValue ),
        calorieRange = IntRange( labelRangeSliderCalories.lowValue.toInt, labelRangeSliderCalories.highValue.toInt ),
        mashEfficiencyRange = IntRange( labelRangeSliderMashEfficiency.lowValue.toInt, labelRangeSliderMashEfficiency.highValue.toInt ),
        brewhouseEfficiencyRange = IntRange( labelRangeSliderBrewhouseEfficiency.lowValue.toInt, labelRangeSliderBrewhouseEfficiency.highValue.toInt )
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
          text = s"${tempRangeDuration.modelTempRange.low} - ${tempRangeDuration.modelTempRange.high}, " +
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
          text = s"${tempRangeDuration.modelTempRange.low} - ${tempRangeDuration.modelTempRange.high}, " +
                 s"${tempRangeDuration.duration} ${tempRangeDuration.unit.toString}"
          tempRangeDuration
        case _ => value.value
    }

  val labelCoolingTempRange = Label( context.labelCoolingTempRange )
  val labelRangeSliderCoolingTemp = LabelRangeSlider(
    min = 67,
    max = 73,
    increment = 1,
    low = model.selectedRecipe.value.coolingTempRange.low,
    high = model.selectedRecipe.value.coolingTempRange.high,
    lowFunction = enableSave,
    highFunction = enableSave)

  val labelFermentingTempRangeDuration = Label( context.labelFermentingTempRangeDuration )
  val labelButtonFermentingTempRangeDuration = new LabelButton[TempRangeDuration]:
    text.onChange { (_, _, _) => enableSave() }
    value = model.selectedRecipe.value.fermentingTempRangeDuration
    buttonAction = () => {
      TempRangeDurationDialog(context, value.value).showAndWait() match
        case Some(tempRangeDuration: TempRangeDuration) =>
          text = s"${tempRangeDuration.modelTempRange.low} - ${tempRangeDuration.modelTempRange.high}, " +
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
          text = s"${tempRangeDuration.modelTempRange.low} - ${tempRangeDuration.modelTempRange.high}, " +
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
          text = s"${tempRangeDuration.modelTempRange.low} - ${tempRangeDuration.modelTempRange.high}, " +
                 s"${tempRangeDuration.duration} ${tempRangeDuration.unit.toString}"
          tempRangeDuration
        case _ => value.value
    }

  val labelPh = Label( context.labelPh )
  val textFieldPh = new DoubleTextField():
    text.onChange { (_, _, _) => enableSave() }

  val labelOriginalGravityRange = Label( context.labelOriginalGravityRange )
  val labelRangeSliderOriginalGravity = new LabelRangeSlider(
    min = 1.000,
    max = 1.130,
    increment = 0.01,
    low = model.selectedRecipe.value.originalGravityRange.low.formatGravity,
    high = model.selectedRecipe.value.originalGravityRange.high.formatGravity,
    lowFunction = enableSave,
    highFunction = enableSave,
    format = Format.asGravity)

  val labelFinalGravityRange = Label( context.labelFinalGravityRange )
  val labelRangeSliderFinalGravity = LabelRangeSlider(
    min = 1.000,
    max = 1.130,
    increment = 0.01,
    low = model.selectedRecipe.value.finalGravityRange.low.formatGravity,
    high = model.selectedRecipe.value.finalGravityRange.high.formatGravity,
    lowFunction = enableSave,
    highFunction = enableSave,
    format = Format.asGravity)

  val labelSrmColorRange = Label( context.labelSrmColorRange )
  val labelRangeSliderSrmColor = LabelRangeSlider(
    min = 1,
    max = 40,
    increment = 1,
    low = model.selectedRecipe.value.srmColorRange.low,
    high = model.selectedRecipe.value.srmColorRange.high,
    lowFunction = enableSave,
    highFunction = enableSave)

  val labelIbuBitternessRange = Label( context.labelIbuBitternessRange )
  val labelRangeSliderIbuBitterness = LabelRangeSlider(
    min = 0,
    max = 120,
    increment = 10,
    low = model.selectedRecipe.value.ibuBitternessRange.low,
    high = model.selectedRecipe.value.ibuBitternessRange.high,
    lowFunction = enableSave,
    highFunction = enableSave)

  val labelAlcoholByVolumeRange = Label( context.labelAlcoholByVolumeRange )
  val labelRangeSliderAlcoholByVolume = LabelRangeSlider(
    min = 1.0,
    max = 13.0,
    increment = 1.0,
    low = model.selectedRecipe.value.alcoholByVolumeRange.low.format,
    high = model.selectedRecipe.value.alcoholByVolumeRange.high.format,
    lowFunction = enableSave,
    highFunction = enableSave,
    format = Format.asDouble)

  val labelAlcoholByWeightRange = Label( context.labelAlcoholByWeightRange )
  val labelRangeSliderAlcoholByWeight = LabelRangeSlider(
    min = 1.0,
    max = 10.0,
    increment = 1.0,
    low = model.selectedRecipe.value.alcoholByWeightRange.low.format,
    high = model.selectedRecipe.value.alcoholByWeightRange.high.format,
    lowFunction = enableSave,
    highFunction = enableSave,
    format = Format.asDouble)

  val labelCalorieRange = Label( context.labelCalorieRange )
  val labelRangeSliderCalories = LabelRangeSlider(
    min = 10,
    max = 600,
    increment = 50,
    low = model.selectedRecipe.value.calorieRange.low,
    high = model.selectedRecipe.value.calorieRange.high,
    lowFunction = enableSave,
    highFunction = enableSave)

  val labelMashEfficiencyRange = Label( context.labelMashEfficiencyRange )
  val labelRangeSliderMashEfficiency = LabelRangeSlider(
    min = 50,
    max = 100,
    increment = 10,
    low = model.selectedRecipe.value.mashEfficiencyRange.low,
    high = model.selectedRecipe.value.mashEfficiencyRange.high,
    lowFunction = enableSave,
    highFunction = enableSave)

  val labelBrewhouseEfficiencyRange = Label( context.labelBrewhouseEfficiencyRange )
  val labelRangeSliderBrewhouseEfficiency = LabelRangeSlider(
    min = 50,
    max = 100,
    increment = 10,
    low = model.selectedRecipe.value.brewhouseEfficiencyRange.low,
    high = model.selectedRecipe.value.brewhouseEfficiencyRange.high,
    lowFunction = enableSave,
    highFunction = enableSave)

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
    labelCoolingTempRange -> labelRangeSliderCoolingTemp,
    labelFermentingTempRangeDuration -> labelButtonFermentingTempRangeDuration,
    labelPotentialFermentableExtract -> textFieldPotentialFermentableExtract,
    labelConditioningTempRangeDuration -> labelButtonConditioningTempRangeDuration,
    labelKeggingTempRangeDuration -> labelButtonKeggingTempRangeDuration,
    labelPh -> textFieldPh,
    labelOriginalGravityRange -> labelRangeSliderOriginalGravity,
    labelFinalGravityRange -> labelRangeSliderFinalGravity,
    labelSrmColorRange -> labelRangeSliderSrmColor,
    labelIbuBitternessRange -> labelRangeSliderIbuBitterness,
    labelAlcoholByVolumeRange -> labelRangeSliderAlcoholByVolume,
    labelAlcoholByWeightRange -> labelRangeSliderAlcoholByWeight,
    labelCalorieRange -> labelRangeSliderCalories,
    labelMashEfficiencyRange -> labelRangeSliderMashEfficiency,
    labelBrewhouseEfficiencyRange -> labelRangeSliderBrewhouseEfficiency,
    labelCreated -> labelFieldCreated
  )
  val controlGrid = ControlGrid(
    controls,
    List(
      ColumnConstraints(
        minWidth = 200.0,
        prefWidth = 200.0,
        maxWidth = 1000.0,
        hgrow = Priority.Always,
        halignment = HPos.Left,
        fillWidth = true      
      ),
      ColumnConstraints(
        minWidth = 250.0,
        prefWidth = 250.0,
        maxWidth = 1000.0,
        hgrow = Priority.Always,
        halignment = HPos.Left,
        fillWidth = true
      ) 
    )
  )

  val scrollPaneControls = new ScrollPane:
    content = controlGrid

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