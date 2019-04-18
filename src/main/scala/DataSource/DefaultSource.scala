package DataSource

import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.sources.{BaseRelation, RelationProvider, SchemaRelationProvider}
import org.apache.spark.sql.types.StructType

/**
  * 数据格式：
  * 1000,ruoze,0,10000,200000
  */
class DefaultSource extends RelationProvider with SchemaRelationProvider {


  //重写SchemaRelationProvider里面的方法
  def createRelation(sqlContext: SQLContext, parameters: Map[String, String], schema: StructType): BaseRelation = {

    //非常重要，读文件必然使用path
    val path = parameters.get("path")

    path match {
      case Some(p) => new TextRelation(sqlContext, p, schema)
      case _ => throw new IllegalArgumentException("path is requied for custom-text-atasource-api ")
    }

  }

  //重写RelationProvider里面的方法
  override def createRelation(sqlContext: SQLContext, parameters: Map[String, String]): BaseRelation = {
    createRelation(sqlContext, parameters, null)

  }

}
