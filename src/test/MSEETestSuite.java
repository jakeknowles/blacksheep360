package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AuthorTest.class,
	ConferenceTest.class,
	ProgramChairTest.class,
	ReviewerTest.class,
	SubProgramChairTest.class,
	UserTest.class,
	ManuscriptTest.class,
	ReviewTest.class,
	RecommendationTest.class
})

public class MSEETestSuite {


}
