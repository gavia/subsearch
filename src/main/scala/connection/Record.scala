package connection

class Record private (val recordType: String, val name: String, val data: String)

object Record {
  def apply(recordType: String, name: String, data: String): Record = {
    val importantData =
      if (recordType == "CNAME")
        data.stripSuffix(".").trim
      else if (recordType == "SRV")
        data.split(" ")(3).stripSuffix(".").trim
      else
        data

    new Record(recordType, name.stripSuffix(".").trim, importantData)
  }

  def unapply(record: Record): Option[(String, String, String)] =
    Some((record.recordType, record.name, record.data))
}
