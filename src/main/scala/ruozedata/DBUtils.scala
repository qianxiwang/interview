package ruozedata

import scala.util.Random

object DBUtils {

  def getConnection() = {
    new Random().nextInt(100) + ""
  }

  def returnConnection(connection: String): Unit = {

  }

}
