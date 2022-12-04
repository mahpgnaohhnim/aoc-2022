package day02.enums

enum class GameOption {
    ROCK {
        override fun shapeScore() = 1
        override fun winTo() = SCISSOR
        override fun looseTo() = PAPER
    },
    PAPER {
        override fun shapeScore() = 2
        override fun winTo() = ROCK
        override fun looseTo() = SCISSOR
    },
    SCISSOR {
        override fun shapeScore() = 3
        override fun winTo() = PAPER
        override fun looseTo() = ROCK
    };

    abstract fun shapeScore(): Int
    abstract fun winTo(): GameOption
    abstract fun looseTo(): GameOption
}