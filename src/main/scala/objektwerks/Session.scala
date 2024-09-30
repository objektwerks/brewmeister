package objektwerks

/* 
Plato	SG (Specific Gravity)
10	1.000
12	1.048
14	1.056
16	1.064
18	1.072
*/
final case class Session(id: Long,
                         recipeId: Long,
                         originalGravity: Double, // 1.000 - 1.072
                         finalGravity: Double, // 1.000 - 1.072
                         color: Int, // 1 - 40
                         hopBitterness: Int, // 0 - 120
                         alcoholByVolume: Double, // 3.0 - 13.0
                         alcoholByWeight: Double,
                         calories: Double,
                         mashEfficiency: Double,
                         brewhouseEfficiency: Double)