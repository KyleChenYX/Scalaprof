package edu.neu.coe.scala.numerics

import org.scalatest.{ FlatSpec, Matchers }
import Rational.RationalHelper

/**
 * @author scalaprof
 */
class RationalSpec extends FlatSpec with Matchers {
  
  val half = Rational(1,2)

	"0" should "be OK" in {
		Rational(0)
	}
  it should "use implicit conversion" in {
    val r: Rational = 0
    r shouldBe (Rational.zero)
  }
  it should "be ZERO" in {
    Rational(0) shouldBe (Rational.zero)
  }
	it should "be whole" in {
		Rational.zero shouldBe 'whole
	}
	it should "be zero" in {
		Rational.zero shouldBe 'zero
	}
	it should "equal 0" in {
		(Rational.zero.toInt) should be (0)
	}
	it should "equal infinity when inverted" in {
		(Rational.zero.invert) shouldBe 'infinity
	}
  it should "equal BigDecimal.ZERO" in {
    Rational.zero.toBigDecimal shouldBe BigDecimal(0)
  }
  it should "equal r when added to r" in {
    val r = Rational(22,7) // we could choose anything here
    (Rational.zero+r) should be (r)
  }

  "1/2" should "be OK" in {
    half * 2 shouldBe (Rational.one)
  }
  it should "equal HALF" in {
    Rational("1/2") shouldBe (half)
  }
  it should "be half of one" in {
    half * 2 shouldBe (Rational.one)
  }
  it should "be OK using r-interpolator" in {
    r"1/2" * 2 shouldBe (Rational.one)
  }
  it should "be OK using r-interpolator with variable" in {
    val denom = 2
    r"1/$denom" * denom shouldBe (Rational.one)
  }
  
  "1" should "be OK" in {
    Rational(1)
  }
  it should "be one" in {
    Rational(1) shouldBe (Rational.one)
  }
  it should "be whole" in {
    Rational.one shouldBe 'whole
  }
  it should "be unity" in {
    Rational.one shouldBe 'unity
  }
  it should "equal 1" in {
    (Rational.one.toInt) should be (1)
  }
  it should "not equal infinity when inverted" in {
    (Rational.one.invert) should not be 'infinity
  }
  it should "equal itself when inverted" in {
    (Rational.one.invert) should be (Rational.one)
  }
  it should "equal BigDecimal.one" in {
    Rational.one.toBigDecimal shouldBe BigDecimal(1)
  }
  it should "equal r when multiplied by r" in {
    val r = Rational(22,7) // we could choose anything here
    (Rational.one*r) should be (r)
  }

	"10" should "be OK" in {
		Rational(10)
	}
  it should "be ten" in {
    Rational(10) shouldBe (Rational.ten)
  }
	it should "be whole" in {
		Rational.ten shouldBe 'whole
	}
	it should "not be zero" in {
		Rational.ten should not be 'zero
	}
	it should "equal 10" in {
		(Rational.ten.toInt) should  be (10)
	}
  it should "equal 5*2" in {
    (Rational.ten/2) should  be (Rational(5))
  }
  it should "equal 10*1" in {
    (Rational.ten/10) should  be (Rational.one)
  }
  it should "equal BigDecimal(10)" in {
    Rational.ten.toBigDecimal shouldBe BigDecimal(10)
  }
  it should "equal a million when raised to 6th power" in {
    (Rational.ten^6) should  be (Rational(1000000))
  }
  it should "barf when raised to 10th power" in {
    // not sure why there should be a stack overflow -- but we do expect an exception
    an [StackOverflowError] should be thrownBy Rational.ten.power(10).toInt
  }

	"2/3" should "be OK" in {
		Rational(2,3)
	}
  it should "equal -1/3 when added to -1" in {
    Rational(2,3)-Rational.one should be (Rational(-1,3))
  }
  it should "be less than 1" in {
    Rational(2,3).compare(Rational.one) should be (-1)
  }
	it should "not be whole" in {
		Rational(2,3) should not be 'whole
	}
	it should "equal 2 when multiplied by 3" in {
		(Rational(2,3)*3 toInt) should be (2)
	}
	it should "equal 3/2 when inverted" in {
		(Rational(2,3).invert) should be (Rational(3,2))
	}
  it should "equal 5/3 when added to 1" in {
    (Rational.one+Rational(2,3)) should be (Rational(5,3))
  }
  it should "equal 4/9 when multiplied by itself" in {
    val r = Rational(2,3)
    (r*r) should be (Rational(4,9))
  }
  it should "equal 4/9 when squared" in {
    (Rational(2,3)^2) should be (Rational(4,9))
  }
  it should "barf when toInt invoked" in {
    val thrown = the [Exception] thrownBy Rational(2,3).toInt
    thrown.getMessage should equal ("2/3 is not Whole")
  }

	"2/4" should "not be OK" in {
		val thrown = the [IllegalArgumentException] thrownBy Rational(2,4)
	  thrown.getMessage should equal ("requirement failed: Rational(2,4): arguments have common factor")
	}
	it should "be OK via normalize" in {
		Rational.normalize(2,4)
	}
  
  "Floating Point Problem" should "be OK" in {
    val x = Rational(1,10)+Rational.normalize(2,10)
    val y = x * 10 / 3
    y shouldBe 'unity
  }
}