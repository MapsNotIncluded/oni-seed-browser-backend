import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import kotlinx.serialization.json.Json
import model.Cluster
import model.filter.FilterQuery
import org.mapsnotincluded.search.SearchIndexDatabase
import java.util.Properties
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.measureTime

class DatabaseTest {

    private val testFilter = """
        {
           "cluster":"FRST-C",
           "dlcs":[
              "SpacedOut"
           ],
           "mode":"SPACEDOUT_SPACEDOUT",
           "rules":[
              [
                 {
                    "asteroid":"ForestMoonlet",
                    "geyserCount":null,
                    "geyserOutput":null,
                    "worldTrait":{
                       "has":true,
                       "worldTrait":"GeoActive"
                    },
                    "spaceDestinationCount":null
                 }
              ],
              [
                 {
                    "asteroid":"ForestMoonlet",
                    "geyserCount":null,
                    "geyserOutput":{
                       "geyser":"hot_water",
                       "condition":"AT_LEAST",
                       "outputInGramPerSecond":1
                    },
                    "worldTrait":null,
                    "spaceDestinationCount":null
                 },
                 {
                    "asteroid":"ForestMoonlet",
                    "geyserCount":{
                       "geyser":"hot_water",
                       "condition":"AT_LEAST",
                       "count":1
                    },
                    "geyserOutput":null,
                    "worldTrait":null,
                    "spaceDestinationCount":null
                 }
              ]
           ]
        }
    """.trimIndent()

    @Test
    fun testFilter() {

        val testClusterJson = DatabaseTest::class.java.getResource("clusters.json")!!.readText()

        val testClusters = Json.decodeFromString<List<Cluster>>(testClusterJson)

        val driver: SqlDriver = JdbcSqliteDriver(
            "jdbc:sqlite::memory:",
            Properties(),
            SearchIndexDatabase.Schema
        )

        val database = SearchIndexDatabase(driver)

        for (cluster in testClusters)
            Database.addToSearchIndex(cluster, database)

        val filterGenerator = SqlFilterGenerator(database.clusterSummaryQueries)

        val filterQuery = Json.decodeFromString<FilterQuery>(testFilter)

        val executionTime = measureTime {

            val coordinates = filterGenerator.executeFilter(filterQuery)

            assertEquals(
                expected = listOf(
                    "FRST-C-1416591624-0-0-0",
                    "FRST-C-999274608-0-0-0"
                ),
                actual = coordinates,
            )
        }

        println("Execution time: ${executionTime.inWholeMilliseconds} ms")
    }
}
