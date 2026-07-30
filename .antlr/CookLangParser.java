// Generated from c:/Users/marti/Desktop/COOKLANG/CookLang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class CookLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		INGREDIENTS=32, PROCEDURE=33, PRINT=34, IF=35, ELSE=36, WHILE=37, FOR=38, 
		FROM=39, TO=40, EXIT=41, NEW=42, TRUE=43, FALSE=44, INT_T=45, FLOAT_T=46, 
		BOOL_T=47, STRING_T=48, GRAM_T=49, ML_T=50, TEMP_T=51, FLOAT=52, INT=53, 
		STRING=54, ISTRING=55, ID=56, LINE_COMMENT=57, BLOCK_COMMENT=58, WS=59;
	public static final int
		RULE_program = 0, RULE_ingredientSection = 1, RULE_procedureSection = 2, 
		RULE_block = 3, RULE_statement = 4, RULE_varDecl = 5, RULE_assign = 6, 
		RULE_assignOp = 7, RULE_incDec = 8, RULE_varType = 9, RULE_baseType = 10, 
		RULE_expr = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "ingredientSection", "procedureSection", "block", "statement", 
			"varDecl", "assign", "assignOp", "incDec", "varType", "baseType", "expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "'{'", "'}'", "';'", "'('", "')'", "'='", "'['", "']'", 
			"'+='", "'-='", "'*='", "'/='", "'++'", "'--'", "'!'", "'-'", "'*'", 
			"'/'", "'%'", "'+'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'&&'", 
			"'||'", "'?'", "','", "'ingredients'", "'procedure'", "'print'", "'if'", 
			"'else'", "'while'", "'for'", "'from'", "'to'", "'exit'", "'new'", "'true'", 
			"'false'", "'Int'", "'Float'", "'Bool'", "'String'", "'Gram'", "'Ml'", 
			"'Temp'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "INGREDIENTS", "PROCEDURE", 
			"PRINT", "IF", "ELSE", "WHILE", "FOR", "FROM", "TO", "EXIT", "NEW", "TRUE", 
			"FALSE", "INT_T", "FLOAT_T", "BOOL_T", "STRING_T", "GRAM_T", "ML_T", 
			"TEMP_T", "FLOAT", "INT", "STRING", "ISTRING", "ID", "LINE_COMMENT", 
			"BLOCK_COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CookLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CookLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public ProcedureSectionContext procedureSection() {
			return getRuleContext(ProcedureSectionContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CookLangParser.EOF, 0); }
		public IngredientSectionContext ingredientSection() {
			return getRuleContext(IngredientSectionContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INGREDIENTS) {
				{
				setState(24);
				ingredientSection();
				}
			}

			setState(27);
			procedureSection();
			setState(28);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IngredientSectionContext extends ParserRuleContext {
		public TerminalNode INGREDIENTS() { return getToken(CookLangParser.INGREDIENTS, 0); }
		public List<VarDeclContext> varDecl() {
			return getRuleContexts(VarDeclContext.class);
		}
		public VarDeclContext varDecl(int i) {
			return getRuleContext(VarDeclContext.class,i);
		}
		public IngredientSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ingredientSection; }
	}

	public final IngredientSectionContext ingredientSection() throws RecognitionException {
		IngredientSectionContext _localctx = new IngredientSectionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_ingredientSection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(INGREDIENTS);
			setState(31);
			match(T__0);
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4468415255281664L) != 0)) {
				{
				{
				setState(32);
				varDecl();
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProcedureSectionContext extends ParserRuleContext {
		public TerminalNode PROCEDURE() { return getToken(CookLangParser.PROCEDURE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ProcedureSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureSection; }
	}

	public final ProcedureSectionContext procedureSection() throws RecognitionException {
		ProcedureSectionContext _localctx = new ProcedureSectionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_procedureSection);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(PROCEDURE);
			setState(39);
			match(T__0);
			setState(40);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(T__1);
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 76528672172982276L) != 0)) {
				{
				{
				setState(43);
				statement();
				}
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileStatementContext extends StatementContext {
		public TerminalNode WHILE() { return getToken(CookLangParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public WhileStatementContext(StatementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrintStatementContext extends StatementContext {
		public TerminalNode PRINT() { return getToken(CookLangParser.PRINT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrintStatementContext(StatementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockStatementContext extends StatementContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockStatementContext(StatementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExitStatementContext extends StatementContext {
		public TerminalNode EXIT() { return getToken(CookLangParser.EXIT, 0); }
		public ExitStatementContext(StatementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignStatementContext extends StatementContext {
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public AssignStatementContext(StatementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForStatementContext extends StatementContext {
		public TerminalNode FOR() { return getToken(CookLangParser.FOR, 0); }
		public TerminalNode ID() { return getToken(CookLangParser.ID, 0); }
		public TerminalNode FROM() { return getToken(CookLangParser.FROM, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode TO() { return getToken(CookLangParser.TO, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForStatementContext(StatementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IncDecStatementContext extends StatementContext {
		public IncDecContext incDec() {
			return getRuleContext(IncDecContext.class,0);
		}
		public IncDecStatementContext(StatementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends StatementContext {
		public TerminalNode IF() { return getToken(CookLangParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(CookLangParser.ELSE, 0); }
		public IfStatementContext(StatementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclStatementContext extends StatementContext {
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public DeclStatementContext(StatementContext ctx) { copyFrom(ctx); }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_statement);
		int _la;
		try {
			setState(88);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new DeclStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				varDecl();
				}
				break;
			case 2:
				_localctx = new AssignStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				assign();
				setState(53);
				match(T__3);
				}
				break;
			case 3:
				_localctx = new IncDecStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(55);
				incDec();
				setState(56);
				match(T__3);
				}
				break;
			case 4:
				_localctx = new PrintStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(58);
				match(PRINT);
				setState(59);
				expr(0);
				setState(60);
				match(T__3);
				}
				break;
			case 5:
				_localctx = new IfStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(62);
				match(IF);
				setState(63);
				match(T__4);
				setState(64);
				expr(0);
				setState(65);
				match(T__5);
				setState(66);
				block();
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(67);
					match(ELSE);
					setState(68);
					block();
					}
				}

				}
				break;
			case 6:
				_localctx = new WhileStatementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(71);
				match(WHILE);
				setState(72);
				match(T__4);
				setState(73);
				expr(0);
				setState(74);
				match(T__5);
				setState(75);
				block();
				}
				break;
			case 7:
				_localctx = new ForStatementContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(77);
				match(FOR);
				setState(78);
				match(ID);
				setState(79);
				match(FROM);
				setState(80);
				expr(0);
				setState(81);
				match(TO);
				setState(82);
				expr(0);
				setState(83);
				block();
				}
				break;
			case 8:
				_localctx = new ExitStatementContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(85);
				match(EXIT);
				setState(86);
				match(T__3);
				}
				break;
			case 9:
				_localctx = new BlockStatementContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(87);
				block();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDeclContext extends ParserRuleContext {
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(CookLangParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			varType();
			setState(91);
			match(ID);
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(92);
				match(T__6);
				setState(93);
				expr(0);
				}
			}

			setState(96);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignContext extends ParserRuleContext {
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
	 
		public AssignContext() { }
		public void copyFrom(AssignContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayAssignContext extends AssignContext {
		public TerminalNode ID() { return getToken(CookLangParser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AssignOpContext assignOp() {
			return getRuleContext(AssignOpContext.class,0);
		}
		public ArrayAssignContext(AssignContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarAssignContext extends AssignContext {
		public TerminalNode ID() { return getToken(CookLangParser.ID, 0); }
		public AssignOpContext assignOp() {
			return getRuleContext(AssignOpContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VarAssignContext(AssignContext ctx) { copyFrom(ctx); }
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assign);
		try {
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new VarAssignContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				match(ID);
				setState(99);
				assignOp();
				setState(100);
				expr(0);
				}
				break;
			case 2:
				_localctx = new ArrayAssignContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(102);
				match(ID);
				setState(103);
				match(T__7);
				setState(104);
				expr(0);
				setState(105);
				match(T__8);
				setState(106);
				assignOp();
				setState(107);
				expr(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignOpContext extends ParserRuleContext {
		public AssignOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignOp; }
	}

	public final AssignOpContext assignOp() throws RecognitionException {
		AssignOpContext _localctx = new AssignOpContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assignOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 15488L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IncDecContext extends ParserRuleContext {
		public IncDecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incDec; }
	 
		public IncDecContext() { }
		public void copyFrom(IncDecContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostIncDecContext extends IncDecContext {
		public Token op;
		public TerminalNode ID() { return getToken(CookLangParser.ID, 0); }
		public PostIncDecContext(IncDecContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PreIncDecContext extends IncDecContext {
		public Token op;
		public TerminalNode ID() { return getToken(CookLangParser.ID, 0); }
		public PreIncDecContext(IncDecContext ctx) { copyFrom(ctx); }
	}

	public final IncDecContext incDec() throws RecognitionException {
		IncDecContext _localctx = new IncDecContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_incDec);
		int _la;
		try {
			setState(117);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
			case T__14:
				_localctx = new PreIncDecContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				((PreIncDecContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__13 || _la==T__14) ) {
					((PreIncDecContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(114);
				match(ID);
				}
				break;
			case ID:
				_localctx = new PostIncDecContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				match(ID);
				setState(116);
				((PostIncDecContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__13 || _la==T__14) ) {
					((PostIncDecContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarTypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public VarTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varType; }
	}

	public final VarTypeContext varType() throws RecognitionException {
		VarTypeContext _localctx = new VarTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_varType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			baseType();
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(120);
				match(T__7);
				setState(121);
				match(T__8);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BaseTypeContext extends ParserRuleContext {
		public TerminalNode INT_T() { return getToken(CookLangParser.INT_T, 0); }
		public TerminalNode FLOAT_T() { return getToken(CookLangParser.FLOAT_T, 0); }
		public TerminalNode BOOL_T() { return getToken(CookLangParser.BOOL_T, 0); }
		public TerminalNode STRING_T() { return getToken(CookLangParser.STRING_T, 0); }
		public TerminalNode GRAM_T() { return getToken(CookLangParser.GRAM_T, 0); }
		public TerminalNode ML_T() { return getToken(CookLangParser.ML_T, 0); }
		public TerminalNode TEMP_T() { return getToken(CookLangParser.TEMP_T, 0); }
		public BaseTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseType; }
	}

	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4468415255281664L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntExprContext extends ExprContext {
		public TerminalNode INT() { return getToken(CookLangParser.INT, 0); }
		public IntExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IncDecExprContext extends ExprContext {
		public IncDecContext incDec() {
			return getRuleContext(IncDecContext.class,0);
		}
		public IncDecExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParenExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringExprContext extends ExprContext {
		public TerminalNode STRING() { return getToken(CookLangParser.STRING, 0); }
		public StringExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IndexExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IndexExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FloatExprContext extends ExprContext {
		public TerminalNode FLOAT() { return getToken(CookLangParser.FLOAT, 0); }
		public FloatExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InterpExprContext extends ExprContext {
		public TerminalNode ISTRING() { return getToken(CookLangParser.ISTRING, 0); }
		public InterpExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExprContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnaryExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TernaryExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TernaryExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AddExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public LogicExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MulExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayLiteralExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArrayLiteralExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoolExprContext extends ExprContext {
		public TerminalNode TRUE() { return getToken(CookLangParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(CookLangParser.FALSE, 0); }
		public BoolExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public RelExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewArrayExprContext extends ExprContext {
		public TerminalNode NEW() { return getToken(CookLangParser.NEW, 0); }
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NewArrayExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdExprContext extends ExprContext {
		public TerminalNode ID() { return getToken(CookLangParser.ID, 0); }
		public IdExprContext(ExprContext ctx) { copyFrom(ctx); }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(127);
				match(T__4);
				setState(128);
				expr(0);
				setState(129);
				match(T__5);
				}
				break;
			case 2:
				{
				_localctx = new IncDecExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(131);
				incDec();
				}
				break;
			case 3:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132);
				((UnaryExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
					((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(133);
				expr(14);
				}
				break;
			case 4:
				{
				_localctx = new NewArrayExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134);
				match(NEW);
				setState(135);
				baseType();
				setState(136);
				match(T__7);
				setState(137);
				expr(0);
				setState(138);
				match(T__8);
				}
				break;
			case 5:
				{
				_localctx = new ArrayLiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(140);
				match(T__7);
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 139642374774309152L) != 0)) {
					{
					setState(141);
					expr(0);
					setState(146);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__30) {
						{
						{
						setState(142);
						match(T__30);
						setState(143);
						expr(0);
						}
						}
						setState(148);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(151);
				match(T__8);
				}
				break;
			case 6:
				{
				_localctx = new IntExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(152);
				match(INT);
				}
				break;
			case 7:
				{
				_localctx = new FloatExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(153);
				match(FLOAT);
				}
				break;
			case 8:
				{
				_localctx = new BoolExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(154);
				_la = _input.LA(1);
				if ( !(_la==TRUE || _la==FALSE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 9:
				{
				_localctx = new StringExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(155);
				match(STRING);
				}
				break;
			case 10:
				{
				_localctx = new InterpExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(156);
				match(ISTRING);
				}
				break;
			case 11:
				{
				_localctx = new IdExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(157);
				match(ID);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(185);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(183);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new MulExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(160);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(161);
						((MulExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1835008L) != 0)) ) {
							((MulExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(162);
						expr(14);
						}
						break;
					case 2:
						{
						_localctx = new AddExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(163);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(164);
						((AddExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__16 || _la==T__20) ) {
							((AddExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(165);
						expr(13);
						}
						break;
					case 3:
						{
						_localctx = new RelExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(166);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(167);
						((RelExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 264241152L) != 0)) ) {
							((RelExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(168);
						expr(12);
						}
						break;
					case 4:
						{
						_localctx = new LogicExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(169);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(170);
						((LogicExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__27 || _la==T__28) ) {
							((LogicExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(171);
						expr(11);
						}
						break;
					case 5:
						{
						_localctx = new TernaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(172);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(173);
						match(T__29);
						setState(174);
						expr(0);
						setState(175);
						match(T__0);
						setState(176);
						expr(9);
						}
						break;
					case 6:
						{
						_localctx = new IndexExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(178);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(179);
						match(T__7);
						setState(180);
						expr(0);
						setState(181);
						match(T__8);
						}
						break;
					}
					} 
				}
				setState(187);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 13);
		case 1:
			return precpred(_ctx, 12);
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		case 4:
			return precpred(_ctx, 9);
		case 5:
			return precpred(_ctx, 15);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001;\u00bd\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0001"+
		"\u0000\u0003\u0000\u001a\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0005\u0001\"\b\u0001\n\u0001\f\u0001%"+
		"\t\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0005\u0003-\b\u0003\n\u0003\f\u00030\t\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004F\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0003\u0004Y\b\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005_\b\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006n\b"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b"+
		"v\b\b\u0001\t\u0001\t\u0001\t\u0003\t{\b\t\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b"+
		"\u0091\b\u000b\n\u000b\f\u000b\u0094\t\u000b\u0003\u000b\u0096\b\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u009f\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0005\u000b\u00b8\b\u000b\n\u000b\f\u000b\u00bb"+
		"\t\u000b\u0001\u000b\u0000\u0001\u0016\f\u0000\u0002\u0004\u0006\b\n\f"+
		"\u000e\u0010\u0012\u0014\u0016\u0000\t\u0002\u0000\u0007\u0007\n\r\u0001"+
		"\u0000\u000e\u000f\u0001\u0000-3\u0001\u0000\u0010\u0011\u0001\u0000+"+
		",\u0001\u0000\u0012\u0014\u0002\u0000\u0011\u0011\u0015\u0015\u0001\u0000"+
		"\u0016\u001b\u0001\u0000\u001c\u001d\u00d2\u0000\u0019\u0001\u0000\u0000"+
		"\u0000\u0002\u001e\u0001\u0000\u0000\u0000\u0004&\u0001\u0000\u0000\u0000"+
		"\u0006*\u0001\u0000\u0000\u0000\bX\u0001\u0000\u0000\u0000\nZ\u0001\u0000"+
		"\u0000\u0000\fm\u0001\u0000\u0000\u0000\u000eo\u0001\u0000\u0000\u0000"+
		"\u0010u\u0001\u0000\u0000\u0000\u0012w\u0001\u0000\u0000\u0000\u0014|"+
		"\u0001\u0000\u0000\u0000\u0016\u009e\u0001\u0000\u0000\u0000\u0018\u001a"+
		"\u0003\u0002\u0001\u0000\u0019\u0018\u0001\u0000\u0000\u0000\u0019\u001a"+
		"\u0001\u0000\u0000\u0000\u001a\u001b\u0001\u0000\u0000\u0000\u001b\u001c"+
		"\u0003\u0004\u0002\u0000\u001c\u001d\u0005\u0000\u0000\u0001\u001d\u0001"+
		"\u0001\u0000\u0000\u0000\u001e\u001f\u0005 \u0000\u0000\u001f#\u0005\u0001"+
		"\u0000\u0000 \"\u0003\n\u0005\u0000! \u0001\u0000\u0000\u0000\"%\u0001"+
		"\u0000\u0000\u0000#!\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000"+
		"$\u0003\u0001\u0000\u0000\u0000%#\u0001\u0000\u0000\u0000&\'\u0005!\u0000"+
		"\u0000\'(\u0005\u0001\u0000\u0000()\u0003\u0006\u0003\u0000)\u0005\u0001"+
		"\u0000\u0000\u0000*.\u0005\u0002\u0000\u0000+-\u0003\b\u0004\u0000,+\u0001"+
		"\u0000\u0000\u0000-0\u0001\u0000\u0000\u0000.,\u0001\u0000\u0000\u0000"+
		"./\u0001\u0000\u0000\u0000/1\u0001\u0000\u0000\u00000.\u0001\u0000\u0000"+
		"\u000012\u0005\u0003\u0000\u00002\u0007\u0001\u0000\u0000\u00003Y\u0003"+
		"\n\u0005\u000045\u0003\f\u0006\u000056\u0005\u0004\u0000\u00006Y\u0001"+
		"\u0000\u0000\u000078\u0003\u0010\b\u000089\u0005\u0004\u0000\u00009Y\u0001"+
		"\u0000\u0000\u0000:;\u0005\"\u0000\u0000;<\u0003\u0016\u000b\u0000<=\u0005"+
		"\u0004\u0000\u0000=Y\u0001\u0000\u0000\u0000>?\u0005#\u0000\u0000?@\u0005"+
		"\u0005\u0000\u0000@A\u0003\u0016\u000b\u0000AB\u0005\u0006\u0000\u0000"+
		"BE\u0003\u0006\u0003\u0000CD\u0005$\u0000\u0000DF\u0003\u0006\u0003\u0000"+
		"EC\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000FY\u0001\u0000\u0000"+
		"\u0000GH\u0005%\u0000\u0000HI\u0005\u0005\u0000\u0000IJ\u0003\u0016\u000b"+
		"\u0000JK\u0005\u0006\u0000\u0000KL\u0003\u0006\u0003\u0000LY\u0001\u0000"+
		"\u0000\u0000MN\u0005&\u0000\u0000NO\u00058\u0000\u0000OP\u0005\'\u0000"+
		"\u0000PQ\u0003\u0016\u000b\u0000QR\u0005(\u0000\u0000RS\u0003\u0016\u000b"+
		"\u0000ST\u0003\u0006\u0003\u0000TY\u0001\u0000\u0000\u0000UV\u0005)\u0000"+
		"\u0000VY\u0005\u0004\u0000\u0000WY\u0003\u0006\u0003\u0000X3\u0001\u0000"+
		"\u0000\u0000X4\u0001\u0000\u0000\u0000X7\u0001\u0000\u0000\u0000X:\u0001"+
		"\u0000\u0000\u0000X>\u0001\u0000\u0000\u0000XG\u0001\u0000\u0000\u0000"+
		"XM\u0001\u0000\u0000\u0000XU\u0001\u0000\u0000\u0000XW\u0001\u0000\u0000"+
		"\u0000Y\t\u0001\u0000\u0000\u0000Z[\u0003\u0012\t\u0000[^\u00058\u0000"+
		"\u0000\\]\u0005\u0007\u0000\u0000]_\u0003\u0016\u000b\u0000^\\\u0001\u0000"+
		"\u0000\u0000^_\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`a\u0005"+
		"\u0004\u0000\u0000a\u000b\u0001\u0000\u0000\u0000bc\u00058\u0000\u0000"+
		"cd\u0003\u000e\u0007\u0000de\u0003\u0016\u000b\u0000en\u0001\u0000\u0000"+
		"\u0000fg\u00058\u0000\u0000gh\u0005\b\u0000\u0000hi\u0003\u0016\u000b"+
		"\u0000ij\u0005\t\u0000\u0000jk\u0003\u000e\u0007\u0000kl\u0003\u0016\u000b"+
		"\u0000ln\u0001\u0000\u0000\u0000mb\u0001\u0000\u0000\u0000mf\u0001\u0000"+
		"\u0000\u0000n\r\u0001\u0000\u0000\u0000op\u0007\u0000\u0000\u0000p\u000f"+
		"\u0001\u0000\u0000\u0000qr\u0007\u0001\u0000\u0000rv\u00058\u0000\u0000"+
		"st\u00058\u0000\u0000tv\u0007\u0001\u0000\u0000uq\u0001\u0000\u0000\u0000"+
		"us\u0001\u0000\u0000\u0000v\u0011\u0001\u0000\u0000\u0000wz\u0003\u0014"+
		"\n\u0000xy\u0005\b\u0000\u0000y{\u0005\t\u0000\u0000zx\u0001\u0000\u0000"+
		"\u0000z{\u0001\u0000\u0000\u0000{\u0013\u0001\u0000\u0000\u0000|}\u0007"+
		"\u0002\u0000\u0000}\u0015\u0001\u0000\u0000\u0000~\u007f\u0006\u000b\uffff"+
		"\uffff\u0000\u007f\u0080\u0005\u0005\u0000\u0000\u0080\u0081\u0003\u0016"+
		"\u000b\u0000\u0081\u0082\u0005\u0006\u0000\u0000\u0082\u009f\u0001\u0000"+
		"\u0000\u0000\u0083\u009f\u0003\u0010\b\u0000\u0084\u0085\u0007\u0003\u0000"+
		"\u0000\u0085\u009f\u0003\u0016\u000b\u000e\u0086\u0087\u0005*\u0000\u0000"+
		"\u0087\u0088\u0003\u0014\n\u0000\u0088\u0089\u0005\b\u0000\u0000\u0089"+
		"\u008a\u0003\u0016\u000b\u0000\u008a\u008b\u0005\t\u0000\u0000\u008b\u009f"+
		"\u0001\u0000\u0000\u0000\u008c\u0095\u0005\b\u0000\u0000\u008d\u0092\u0003"+
		"\u0016\u000b\u0000\u008e\u008f\u0005\u001f\u0000\u0000\u008f\u0091\u0003"+
		"\u0016\u000b\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0091\u0094\u0001"+
		"\u0000\u0000\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0092\u0093\u0001"+
		"\u0000\u0000\u0000\u0093\u0096\u0001\u0000\u0000\u0000\u0094\u0092\u0001"+
		"\u0000\u0000\u0000\u0095\u008d\u0001\u0000\u0000\u0000\u0095\u0096\u0001"+
		"\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u0097\u009f\u0005"+
		"\t\u0000\u0000\u0098\u009f\u00055\u0000\u0000\u0099\u009f\u00054\u0000"+
		"\u0000\u009a\u009f\u0007\u0004\u0000\u0000\u009b\u009f\u00056\u0000\u0000"+
		"\u009c\u009f\u00057\u0000\u0000\u009d\u009f\u00058\u0000\u0000\u009e~"+
		"\u0001\u0000\u0000\u0000\u009e\u0083\u0001\u0000\u0000\u0000\u009e\u0084"+
		"\u0001\u0000\u0000\u0000\u009e\u0086\u0001\u0000\u0000\u0000\u009e\u008c"+
		"\u0001\u0000\u0000\u0000\u009e\u0098\u0001\u0000\u0000\u0000\u009e\u0099"+
		"\u0001\u0000\u0000\u0000\u009e\u009a\u0001\u0000\u0000\u0000\u009e\u009b"+
		"\u0001\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009e\u009d"+
		"\u0001\u0000\u0000\u0000\u009f\u00b9\u0001\u0000\u0000\u0000\u00a0\u00a1"+
		"\n\r\u0000\u0000\u00a1\u00a2\u0007\u0005\u0000\u0000\u00a2\u00b8\u0003"+
		"\u0016\u000b\u000e\u00a3\u00a4\n\f\u0000\u0000\u00a4\u00a5\u0007\u0006"+
		"\u0000\u0000\u00a5\u00b8\u0003\u0016\u000b\r\u00a6\u00a7\n\u000b\u0000"+
		"\u0000\u00a7\u00a8\u0007\u0007\u0000\u0000\u00a8\u00b8\u0003\u0016\u000b"+
		"\f\u00a9\u00aa\n\n\u0000\u0000\u00aa\u00ab\u0007\b\u0000\u0000\u00ab\u00b8"+
		"\u0003\u0016\u000b\u000b\u00ac\u00ad\n\t\u0000\u0000\u00ad\u00ae\u0005"+
		"\u001e\u0000\u0000\u00ae\u00af\u0003\u0016\u000b\u0000\u00af\u00b0\u0005"+
		"\u0001\u0000\u0000\u00b0\u00b1\u0003\u0016\u000b\t\u00b1\u00b8\u0001\u0000"+
		"\u0000\u0000\u00b2\u00b3\n\u000f\u0000\u0000\u00b3\u00b4\u0005\b\u0000"+
		"\u0000\u00b4\u00b5\u0003\u0016\u000b\u0000\u00b5\u00b6\u0005\t\u0000\u0000"+
		"\u00b6\u00b8\u0001\u0000\u0000\u0000\u00b7\u00a0\u0001\u0000\u0000\u0000"+
		"\u00b7\u00a3\u0001\u0000\u0000\u0000\u00b7\u00a6\u0001\u0000\u0000\u0000"+
		"\u00b7\u00a9\u0001\u0000\u0000\u0000\u00b7\u00ac\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b2\u0001\u0000\u0000\u0000\u00b8\u00bb\u0001\u0000\u0000\u0000"+
		"\u00b9\u00b7\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000"+
		"\u00ba\u0017\u0001\u0000\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000"+
		"\u000e\u0019#.EX^muz\u0092\u0095\u009e\u00b7\u00b9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}