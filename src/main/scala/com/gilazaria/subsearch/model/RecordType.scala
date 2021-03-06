package com.gilazaria.subsearch.model

import org.xbill.DNS.Type

case class RecordType(stringValue: String) extends Ordered[RecordType] {
  override def toString = stringValue
  lazy val intValue: Int = Type.value(stringValue)

  def isOneOf(types: String*) =
    types.contains(stringValue)

  override def compare(that: RecordType): Int =
    if (this.stringValue == that.stringValue) 0
    else if (this.stringValue > that.stringValue) 1
    else -1
}

object RecordType {
  lazy val A      = RecordType("A")
  lazy val AAAA   = RecordType("AAAA")
  lazy val ANY    = RecordType("ANY")
  lazy val AXFR   = RecordType("AXFR")
  lazy val CNAME  = RecordType("CNAME")
  lazy val MX     = RecordType("MX")
  lazy val NS     = RecordType("NS")
  lazy val SOA    = RecordType("SOA")
  lazy val RRSIG  = RecordType("RRSIG")
  lazy val HINFO  = RecordType("HINFO")
  lazy val DNSKEY = RecordType("DNSKEY")
  lazy val NSEC   = RecordType("NSEC")
  lazy val PTR    = RecordType("PTR")
  lazy val SRV    = RecordType("SRV")

  private lazy val types: Set[RecordType] = Set(A, AAAA, ANY, AXFR, CNAME, MX, NS, SOA, RRSIG, HINFO, DNSKEY, NSEC, PTR, SRV)

  def fromInt(int: Int): RecordType = {
    val matchingTypes: Set[RecordType] = types.filter(_.intValue == int)

    if (matchingTypes.isEmpty) RecordType(Type.string(int))
    else matchingTypes.head
  }
}
